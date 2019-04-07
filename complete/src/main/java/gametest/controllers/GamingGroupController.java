package gametest.controllers;

import gametest.enums.ACTIVE;
import gametest.models.Gamer;
import gametest.models.GamingGroup;
import gametest.repo.GamingGroupRepo;
import gametest.services.GamingGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
                System.out.println("G: " + g.getName());
            }

            System.out.println("ACTIVE Group Members of :" +group.getName());
            for (Gamer g : group.getActiveGroupMembers()) {
                System.out.println("G: " + g.getName());
            }

            System.out.println("INACTVE Group Members of :" +group.getName());
            for (Gamer g : group.getInactiveGroupMembers()) {
                System.out.println("G: " +g.getName());
            }

        }

        model.addAttribute("gaming_groups", groups);

        return "gaming_groups";
    }

}
