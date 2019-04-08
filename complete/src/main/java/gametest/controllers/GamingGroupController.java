package gametest.controllers;

import com.sun.javafx.sg.prism.NGShape;
import gametest.enums.ACTIVE;
import gametest.models.Gamer;
import gametest.models.GamingGroup;
import gametest.repo.GamingGroupRepo;
import gametest.services.GamingGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SteveGreen on 07/04/2019.
 */
@Controller
public class GamingGroupController {

    @Autowired
    GamingGroupService gamingGroupService;

    @RequestMapping("/gaming_group_details{groupid}/")
    public String gameGroupDetails(@PathVariable final Long groupid, Model model) {


        GamingGroup gamingGroup = gamingGroupService.getGamingGroupById(groupid);
        // get all information pertaining to that group!

        // it's members
            // active and inactive

        // the games its members have
            //links to them to view:
                //last played
                //bgg details
                //note the owner
                //its popularity

        // locations played at
            //

        // past game sessions
            //present members
            //game played

        // upcoming sessions
            //who is scehduled to come
            //game that we think will be played.
        model.addAttribute("gaming_group", gamingGroup);

        return "gaming_group_details";

    }

    @RequestMapping("/groups")
    public String viewGamingGroups(Model model) {

        //obtain list of groups, and display them.
        List<GamingGroup> groups = gamingGroupService.getAllRegisteredGamingGroups();

        //several debugs

        for (GamingGroup group: groups) {
            System.out.println(group.getName());
            //build group members
            group.setAllGroupMembers(gamingGroupService.getGamersInGamingGroup(group));
            group.updateGroupMembersActivity();
            //brain broken.  end.

            System.out.println("ALL Group Members of :" +group.getName());
            for (Gamer g : group.getAllGroupMembers()) {
                System.out.println("ALL: " + g.getName());
            }

            System.out.println("ACTIVE Group Members of :" +group.getName());
            for (Gamer g : group.getActiveGroupMembers()) {
                System.out.println("ACTIVE: " + g.getName());
            }

            System.out.println("INACTIVE Group Members of :" +group.getName());
            for (Gamer g : group.getInactiveGroupMembers()) {
                System.out.println("INACTIVE: " +g.getName());
            }

        }

        model.addAttribute("gaming_groups", groups);

        return "gaming_groups";
    }

}
