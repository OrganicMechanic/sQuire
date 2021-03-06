package squire.Users;
/**
 * A ProjectFile finder.
 */

import com.avaje.ebean.Finder;
import squire.Users.query.QProject;
import squire.Users.query.QUser;

public class ProjectFileFinder extends Finder<Long, ProjectFile> {
    public ProjectFileFinder() {
        super(ProjectFile.class);
    }

    public QProject where() {
        return new QProject(db());
    }

}
