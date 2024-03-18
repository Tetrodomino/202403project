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
	public String map(@RequestParam(name="f", defaultValue="bname") String field,
			@RequestParam(name="q", defaultValue="") String query,
			HttpSession session, Model model) {
		
		List<Building> buildingList = bSvc.getBuildingList(field, query);
		
		for (int i = 0; i < buildingList.size(); i++)
		{
			String addressroad = buildingList.get(i).getAddressroad();
			if (addressroad.contains("!"))
			{
				addressroad = addressroad.split("!")[0];
			}
			
			Map<String, String> map = mUtil.getGeocode(buildingList.get(i).getAddressroad());
			if (map.get("lat").equals("null") || map.get("lon").equals("null"))
			{
				buildingList.remove(i);
			}
			
			buildingList.get(i).setLat(map.get("lat"));
			buildingList.get(i).setLon(map.get("lon"));
		}
		model.addAttribute("buildingList", buildingList);
		
		return "map";
	}
	
	@GetMapping("detail/{bid}")
	public String detail(@PathVariable int bid, HttpSession session, Model model) {
		
		Building building = bSvc.getBuilding(bid);
		model.addAttribute("building", building);
		
		List<Comment> commentList = cSvc.getCommentList(bid, "");
		model.addAttribute("commentList", commentList);
		
		List<Reply> replyList = rSvc.getReplyList(bid);
		model.addAttribute("replyList", replyList);
				
		return "building/detail";
	}
	
	@GetMapping("deal/{bid}")
	public String deal(@PathVariable int bid, HttpSession session, Model model) {
		List<Integer> areaList = dSvc.getAreaList(bid);
		
		if (areaList.size() == 0)
			return "deal";
		
		List<DealList> dealList = new ArrayList<>();
		
		for (int i = 0; i < areaList.size(); i++)
		{
			int area = areaList.get(i);
			List<Deal> dList = dSvc.getDealListBybidArea(bid, area);
			dealList.add(new DealList(bid, area, dList));
		}
		
		model.addAttribute("dealList", dealList);
		
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
