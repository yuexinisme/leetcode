package com.nick.imdb;

import com.nick.imdb.mapper.MovieMapper;
import com.nick.imdb.pojo.Movie;
import com.nick.imdb.search.MovieSearchRepo;
import com.nick.imdb.util.Select;
import com.nick.imdb.util.Selector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImdbApplicationTests {
@Autowired
MovieMapper movieMapper;
@Autowired
StringRedisTemplate template;
@Autowired
    MovieSearchRepo movieSearchRepo;
@Test
public void test() throws Exception{
    ServerSocket serverSocket=new ServerSocket(8090);
    List<Class> classes=new ArrayList<>();
    classes.add(Selector.class);
    classes.add(Select.class);
    while(true){
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);
        String className = objectInputStream.readUTF();
        String methodName = objectInputStream.readUTF();
        Class<?>[]types= (Class<?>[]) objectInputStream.readObject();
        Object[]params = (Object[]) objectInputStream.readObject();
        Object obj=new Object();
        boolean flag=false;
        for(Class c:classes){
            if(c.getSimpleName().equals(className)){ //simple！
                flag=true;
                Method method = c.getMethod(methodName, types);
                obj = method.invoke(c.newInstance(), params);
                break;
            }
        }
        if(!flag) throw new ClassNotFoundException();
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(obj);
    }
}
    @Test
    public void contextLoad() throws Exception{
        int start = 1;
        int id=0;
        while (start < 1001) {
            Document doc = Jsoup.connect("https://www.imdb.com/search/title?user_rating=1.0,10.0" +
                    "&sort=num_votes,desc&title_type=feature&start=" + start + "&ref_=adv_nxt").get();
            Elements elements = doc.select("div[class=lister-item mode-advanced]");

            for (Element element : elements) {
                id++;
                BoundHashOperations op = template.boundHashOps("movie:" + id);
             //   Movie movie = new Movie();
                Elements eles = element.select("img");
                String title = eles.attr("alt");
              //  movie.setName(title);
                op.put("title",title);
                String image = eles.attr("loadlate").replaceAll("@._V1_.+.jpg", "@._V1_.jpg");
             //   movie.setImage(image);
                op.put("image",image);
                String time=element.select("span[class=lister-item-year text-muted unbold]").text();
              //  System.out.println(time);
                if(String.valueOf(time.charAt(1)).matches("[1-2]")){
                 //   movie.setYear(Integer.valueOf(time.substring(1,5)));
                    op.put("year",time.substring(1,5));
                }else{
                 //   movie.setYear(Integer.valueOf(time.replaceAll("\\([A-Z]+\\) ","").substring(1,5)));
                    op.put("year",time.replaceAll("\\([A-Z]+\\) ","").substring(1,5));
                }
                String rating = element.select("span[class=certificate]").text();
             //   movie.setRating(rating);
                op.put("rating",rating);
                Integer runtime = Integer.valueOf(element.select("span[class=runtime]").text().split(" ")[0]);
             //   movie.setRuntime(runtime);
                op.put("runtime",String.valueOf(runtime));
                String genres = element.select("span[class=genre]").text();
             //   movie.setGenres(genres);
                op.put("genres",genres);
                Double all_rating = Double.valueOf(element.select("strong").text());
            //    movie.setAll_rating(all_rating);
                op.put("all_rating",String.valueOf(all_rating));
                String meta=element.select("span[class=metascore  favorable]").text().trim();
                if(meta!=""&&meta.length()>0) {
                    Short metascore = Short.valueOf(meta);
               //     movie.setMetascore(metascore);
                    op.put("metascore",String.valueOf(metascore));
                }
            //    movie.setPlot(element.select("p[class=text-muted]").text().substring(rating.length()+genres.length()+String.valueOf(runtime).length()+11));
                op.put("plot",element.select("p[class=text-muted]").text().substring(rating.length()+genres.length()+String.valueOf(runtime).length()+11));
                String all=element.select("p[class='']").text();
                System.out.println(all);
                String[] words = all.split(" Stars: | Star: ");
                String director=words[0].replaceAll("Director: ","");
               // movie.setDirector(director.substring(0,director.length()-1));
                op.put("director",director.substring(0,director.length()-1));
              //  movie.setStars(words[1]);
                op.put("stars",words[1]);
                String[]words2=element.select("p[class=sort-num_votes-visible]").text().split(" ");
                // System.out.println(Arrays.toString(words2));
              //  movie.setVotes(Integer.valueOf(words2[1].replace(",","")));
                op.put("votes",words2[1].replace(",",""));
                if(words2.length>=5)
             //   movie.setGross(Double.valueOf(words2[4].replaceAll("\\$","").replace("M","")));
                op.put("gross",words2[4].replaceAll("\\$","").replace("M",""));
          //      movieMapper.insert(movie);

            }
            start+=50;
        }
    }
//@Test
//    public void saveAll(){
//  //  System.setProperty("es.set.netty.runtime.available.processors","false");
//      for(int i=1;i<1001;i++){
//          Movie movie = movieMapper.showImage(i);
//          movie.setAll(movie.getName()+" "+movie.getDirector()+" "+movie.getStars());
//          System.out.println(movie);
//         movieSearchRepo.save(movie);
//      }
//}

}
