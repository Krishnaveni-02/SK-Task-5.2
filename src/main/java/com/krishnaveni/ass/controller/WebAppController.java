package com.krishnaveni.ass.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.krishnaveni.ass.EH.AppNotFoundException;
import com.krishnaveni.ass.model.SubmitResponse;
import com.krishnaveni.ass.model.WebApplications;
import com.krishnaveni.ass.repository.WebAppRepo;

@RestController
@CrossOrigin("http://localhost:3000")
public class WebAppController {

	@Autowired
	private WebAppRepo webapprepo;
	
	// requesting all server objects
	@GetMapping("/findAnApp")
	public ResponseEntity<List <WebApplications>> getAllApps() {
		List<WebApplications> webapps = webapprepo.findAll();
		
		if(webapps.isEmpty()) {
			throw new AppNotFoundException("No applications exits!!");
		} else {
		
		return ResponseEntity.ok(webapps);
		
		}
	}
	
	// requesting a particular server obj
	
	@GetMapping("/findAnAppName/{appName}")
	public ResponseEntity<List<WebApplications>> getAppByName(@PathVariable String appName) {
		List<WebApplications> webapps = webapprepo.findAppsByName(appName); // .forEach(System.out::println)
		
		if(webapps.isEmpty()) {
			throw new AppNotFoundException("Application doesn't exist with name: "+appName);
		}
		
		return ResponseEntity.ok(webapps);
		
	}
	
	@GetMapping("/findAnApp/{id}")
	public ResponseEntity<WebApplications> getAnApp(@PathVariable int id) {
		WebApplications webapps = webapprepo.findById(id).orElseThrow(() -> new AppNotFoundException("No application exists with id: "+id));
		
		return ResponseEntity.ok(webapps);
		
	}
	
	/* saving data
	@PostMapping("/addApp")
	public String saveApp(@RequestBody WebApplications webapps) {
		
		webapprepo.save(webapps);
		
		return "App Data added Successfully";
	}
	*/
	
	@PostMapping("/addApp")
	public ResponseEntity<SubmitResponse> saveApp(@Valid @RequestBody WebApplications webapps, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return new ResponseEntity<SubmitResponse>(HttpStatus.BAD_REQUEST);
		}
		
		webapprepo.save(webapps);
		
		return ResponseEntity.ok(new SubmitResponse("App Data added Successfully"));
	}
	
	@PutMapping("/addApp/{id}")
	public ResponseEntity<WebApplications> updateApp(@PathVariable int id, @RequestBody WebApplications webappDetails) {
		WebApplications webapps = webapprepo.findById(id).orElseThrow(() -> new AppNotFoundException("App doesn't exists with id: "+id));
		
		//webapps.setId(webappDetails.getId());
		webapps.setApp_name(webappDetails.getAppName());
		webapps.setAppDeveloper(webappDetails.getAppDeveloper());
		webapps.setIntroYear(webappDetails.getIntroYear());
		webapps.setPl(webappDetails.getPl());
		webapps.setAppType(webappDetails.getAppType());
		
		WebApplications updatedApp = webapprepo.save(webapps);
		return ResponseEntity.ok(updatedApp);
	}
	
	// delete a server
	@DeleteMapping("/delete/{id}")
	public String deleteApp(@PathVariable int id) {
		
		webapprepo.deleteById(id);
		
		return "Deleted Successfully";
	}
	
	
	
}
