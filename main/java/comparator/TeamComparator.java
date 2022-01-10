package comparator;

import com.foirfoot.model.team.Team;

import java.util.Comparator;

public class TeamComparator implements Comparator<Team> {

    @Override
    public int compare(Team t1, Team t2) {
        int typeResult =  t1.getType().compareTo(t2.getType());
        if(typeResult != 0){
            return typeResult;
        }
        return t1.getCategory().compareTo(t2.getCategory());
    }
}
