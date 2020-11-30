import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import static spark.Spark.*;
import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
/*Chinh sua tren may client*/
    public static void main(String[] args){
        //int numb;
        //ArrayList<Integer> primes=new ArrayList<>();
        HashMap<Integer,ArrayList> hm=new HashMap<>();

        get("/:number",(req,res)->{
            final int num=Integer.parseInt(req.params(":number"));
//
            LoadingCache<String, ArrayList> employeeCache =
                    CacheBuilder.newBuilder()
                            .expireAfterAccess(20, TimeUnit.SECONDS)      // cache will expire after 20sec of access
                            .expireAfterWrite(10,TimeUnit.SECONDS)
                            .build(new CacheLoader<String, ArrayList>() {  // build the cacheloader

                                @Override
                                public ArrayList load(String empId) throws Exception {
                                    //make the expensive call
                                    ArrayList<Integer> primes=new ArrayList<>();
                                    for(int i=2;i<num;i++){
                                        if(isPrime(i)){
                                            primes.add(i);
                                        }
                                    }
                                    System.out.println(primes);
                                    return primes;

                                }
                            });

            //employeeCache.get(String.valueOf(num));
            return employeeCache.get(String.valueOf(num));

        });


    }
    static boolean isPrime(int n)
    {
        // Corner case
        if (n <= 1)
            return false;

        // Check from 2 to n-1
        for (int i = 2; i < n; i++)
            if (n % i == 0)
                return false;

        return true;
    }
}
