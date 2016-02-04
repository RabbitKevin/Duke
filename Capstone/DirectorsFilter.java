import java.util.*;
public class DirectorsFilter implements Filter {
    HashSet<String> directorSet;
    public DirectorsFilter(String directors){
        directorSet = new HashSet<String>();
        String[] directorList = directors.split(",");
        for(int i = 0; i < directorList.length; ++i){
            directorSet.add(directorList[i]);
            //System.out.println(directorList[i]);
        }
    }
    @Override
    public boolean satisfies(String id) {
      String[] directorList = MovieDatabase.getDirector(id).split(",");
      for(int i = 0; i < directorList.length; ++i){
        if(directorSet.contains(directorList[i])) return true;
      }
      return false;
    }
}