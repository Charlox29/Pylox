package utils;

import java.io.Serializable;

public class Cookies implements Serializable {
    private static final long serialVersionUID = 1L;

    private Profile aProfile;
    private String aLastProfilePath;
    private String aLastPort;
    private String aLangage;

    public Cookies(final Profile pProfile, final String pLastprofilePath, final String pLastPort, final String pLangage){
        this.aProfile = pProfile;
        this.aLastProfilePath = pLastprofilePath;
        this.aLastPort = pLastPort;
        this.aLangage = pLangage;
    }

    public Cookies(){
        this(new Profile(), null, null, null);
    }

    public void setProfile(final Profile pProfile){
        this.aProfile = pProfile;
    }

    public void setLastProfilePath(final String pLastprofilePath){
        this.aLastProfilePath = pLastprofilePath;
    }

    public void setLastPort(final String pLastPort){
        this.aLastPort = pLastPort;
    }

    public void setLangage(final String pLangage){
        this.aLangage = pLangage;
    }

    public Profile getProfile() {
        return aProfile;
    }

    public String getLastProfilePath() {
        return aLastProfilePath;
    }

    public String getaLastPort() {
        return aLastPort;
    }

    public String getaLangage() {
        return aLangage;
    }
}
