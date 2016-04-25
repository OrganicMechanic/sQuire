package squire.Users;
/**
 * Implements a project storage model.
 */

import squire.BaseModel;
import squire.Projects.JavaSourceFromString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.UUID;

@Entity
@Table(name = "o_project")
public class Project extends BaseModel {
    public static final ProjectFinder find = new ProjectFinder();

// Database code

    @Column(nullable = false, unique = true)
    private UUID token;

    @Column(nullable = false)
    @ManyToOne
    private User owner;

    @Column()
    private String name;

    @Column()
    private String path;

    @Column()
    private String description;

    @Column()
    private ArrayList<JavaSourceFromString> importedFiles;

    @Column()
    private Integer primaryFileID;

// Legacy code from the non-db Project class, just in case

    public String getToken() {
        return token.toString();
    }

    private void generateToken() {
        this.token = UUID.randomUUID();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public ArrayList<JavaSourceFromString> getFileList() { return importedFiles;}

    public String getMatchingFile(String s)
    {
        String file = "";
        String t;
        for (JavaSourceFromString fileName: this.getFileList())
        {
            t = fileName.getFileName();

            if (s.equals(t))
            {
                //     System.out.println(fileName.substring(file.lastIndexOf("/")+1));
                file = file.toString();
            }
        }
        //   System.out.println(file);
        return file;
    }

}