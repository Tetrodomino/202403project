package com.team3.findmyhome.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.team3.findmyhome.entity.Building;
import com.team3.findmyhome.entity.Comment;
import com.team3.findmyhome.entity.Deal;
import com.team3.findmyhome.entity.DealList;
import com.team3.findmyhome.entity.Reply;
import com.team3.findmyhome.service.BuildingService;
import com.team3.findmyhome.service.CommentService;
import com.team3.findmyhome.service.DealService;
import com.team3.findmyhome.service.ReplyService;
import com.team3.findmyhome.util.JsonUtil;
import com.team3.findmyhome.util.mapUtil;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/building")
public class BuildingController {

	@Autowired private BuildingService bSvc;
	@Autowired private CommentService cSvc;
	@Autowired private ReplyService rSvc;
	@Autowired private DealService dSvc;
	@Autowired private mapUtil mUtil;
	@Autowired private JsonUtil jsonUtil;
	@Value("${spring.servlet.multipart.location}") private String uploadDir;
	
	
	@GetMapping("map")
	public String map(HttpSession session, Model model) {
		
		List<Building> buildingList = bSvc.getBuildingList("bname", "");
		
		for (int i = 0; i < buildingList.size(); i++)
		{
			Building building = buildingList.get(i);
			
			if (building.getLat() == null || building.getLat().equals("") ||
					building.getLon() == null || building.getLon().equals(""))
			{
				String addressroad = building.getAddressroad();
				if (addressroad.contains("!"))
				{
					addressroad = addressroad.split("!")[0];
				}
				
				Map<String, String> map = mUtil.getGeocode(building.getAddressroad());
				if (map.get("lat").equals("null") || map.get("lon").equals("null"))
				{
					bSvc.deleteBuilding(building.getBid());
				}
				
				building.setLat(map.get("lat"));
				building.setLon(map.get("lon"));
				
				bSvc.updateBuildingLatLon(building);
			}
		}
		model.addAttribute("buildingList", buildingList);
		
		return "map";
	}
	
	@GetMapping("search")
	public String searchList(@RequestParam(name="f", defaultValue="bname") String field,
			@RequestParam(name="q", defaultValue="") String query,
			HttpSession session, Model model) {
		
		List<Building> buildingList = bSvc.getBuildingList(field, query);
		
		if (buildingList.size() == 0)
		{
			model.addAttribute("buildingList", buildingList);
			model.addAttribute("field", field);
			model.addAttribute("query", query);
			return "search";
		}
		
		float avgLat = bSvc.getAvgLat(field, query);
		float avgLon = bSvc.getAvgLon(field, query);
		
		model.addAttribute("buildingList", buildingList);
		model.addAttribute("field", field);
		model.addAttribute("query", query);
		model.addAttribute("avgLat", avgLat);
		model.addAttribute("avgLon", avgLon);
		
		return "search";
	}
	
	@GetMapping(value={"detail/{bid}/{field}/{query}", "detail/{bid}/{field}/", "detail/{bid}/{field}", "detail/{bid}"})
	public String detail(@PathVariable int bid, @PathVariable(required=false) String field,
			@PathVariable(required=false) String query, HttpSession session, Model model) {
		
		if (field == null || field.equals(""))
			field = "bname";
		if (query == null || query.equals("null"))
			query = "";
		
		List<Building> buildingList = bSvc.getBuildingList(field, query);
		model.addAttribute("buildingList", buildingList);
		
		Building building = bSvc.getBuilding(bid);
		model.addAttribute("building", building);
		
		float lat = bSvc.getLatBid(bid);
		float lon = bSvc.getLonBid(bid);
		model.addAttribute("lat", lat);
		model.addAttribute("lon", lon);
		
		List<Integer> areaList = dSvc.getAreaList(bid);
		model.addAttribute("areaList", areaList);
		
		//List<Comment> commentList = cSvc.getCommentList(bid, "");
		//model.addAttribute("commentList", commentList);
		
		List<Reply> replyList = rSvc.getReplyList(bid);
		model.addAttribute("replyList", replyList);
				
		return "detail";
	}
	
	@GetMapping("deal/{bid}/{area}")
	public String deal(@PathVariable int bid, @PathVariable int area, HttpSession session, Model model) {
		
		Building building = bSvc.getBuilding(bid);
		model.addAttribute("building", building);
		
		List<Integer> areaList = dSvc.getAreaList(bid);
		if (areaList.size() == 0)
		{	
			return "notfounddeal";
		}
		model.addAttribute("areaList", areaList);
		
		List<Deal> dList = dSvc.getDealListBybidArea(bid, area);
		
		model.addAttribute("dList", dList);
		
		return "deal";
	}
	
	@PostMapping("comment")
	public String reply(int bid, String uid, String content, MultipartHttpServletRequest req,
			HttpSession session, Model model) {
		List<MultipartFile> uploadFileList = req.getFiles("files");

		List<String> fileList = new ArrayList<>();
		for (MultipartFile part: uploadFileList)
		{
			// 첨부 파일이 없는 경우 넘김 - application/octet-stream
			if (part.getContentType().contains("octet-stream"))
				continue;
			
			String filename = part.getOriginalFilename();
			String uploadPath = uploadDir + "upload/" + filename;
			
			try {
				part.transferTo(new File(uploadPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
			fileList.add(filename);
		}
		String files = jsonUtil.list2Json(fileList);
		
		Comment comment = new Comment(bid, uid, content, files);
		cSvc.insertComment(comment);
		
		return "redirect:/building/detail/" + bid;
	}
	
	@PostMapping("reply")
	public String reply(int cid, int bid, String uid, String content, HttpSession session) {
		
		Reply reply = new Reply(cid, bid, uid, content);
		
		rSvc.insertReply(reply);
		
		return "redirect:/building/detail/" + bid;
	}
}
