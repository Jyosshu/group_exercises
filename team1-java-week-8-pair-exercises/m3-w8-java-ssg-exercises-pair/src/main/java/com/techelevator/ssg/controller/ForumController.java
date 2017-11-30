package com.techelevator.ssg.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.ssg.model.forum.ForumDao;
import com.techelevator.ssg.model.forum.ForumPost;

@Controller
public class ForumController {

	@Autowired
	ForumDao forumDao;
	
	@RequestMapping(path="/forum", method=RequestMethod.GET)
	public String showAllPosts(ModelMap modelHolder){
		List<ForumPost> posts = forumDao.getAllPosts();
		modelHolder.put("allPosts", posts );
	return "forum";
}
	
	@RequestMapping(path="/forumInput", method=RequestMethod.GET)
		public String showInput(){
		return "forumInput";
	}
	
	@RequestMapping(path="/forumInput", method=RequestMethod.POST)
		public String postInput(@RequestParam String username, @RequestParam String subject, @RequestParam String message, ModelMap mapHolder){
			ForumPost post = new ForumPost(username, subject, message);
			post.setDatePosted(LocalDateTime.now());
			mapHolder.put("newPost", post);
			
			forumDao.save(post);
	
		return "redirect:/forum";	
	}
	

}
