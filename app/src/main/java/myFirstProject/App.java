/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package myFirstProject;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

public class App {
    public static void main(String[] args) {

        port(getHerokuAssignedPort());
        get("/", (req, res) -> {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", "Mustafa Aydoğan");
            return new ModelAndView(map, "home.mustache");
        }, new MustacheTemplateEngine());

        post("/compute", (req, res) -> {
        System.out.println(req.queryParams("input1"));
        System.out.println(req.queryParams("input2"));

        String input1 = req.queryParams("input1");
        java.util.Scanner sc1 = new java.util.Scanner(input1);
        sc1.useDelimiter("[;\r\n]+");
        java.util.ArrayList<Number> inputList = new java.util.ArrayList<>();
        while (sc1.hasNext())
        {
            double value = Double.parseDouble(sc1.next().replaceAll("\\s",""));
            inputList.add(value);
        }
        sc1.close();
        System.out.println(inputList);

        String input2 = req.queryParams("input2").replaceAll("\\s","");
        double input2AsDouble = Double.parseDouble(input2);

        int result = App.howManyPositiveElements(inputList, input2AsDouble, Integer.class);

        Map<String, String> map = new HashMap<String, String>();
            map.put("result", result + "");
            map.put("message", "number that is greater than " + input2AsDouble);
            map.put("list", "List: " + inputList.toString());
            map.put("input2AsDouble", "The Specific Number: " + input2AsDouble);
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());


        get("/compute",
        (rq, rs) -> {
            Map<String, String> map = new HashMap<String, String>();
            map.put("result", "no computation yet!");
            return new ModelAndView(map, "compute.mustache");
        }, new MustacheTemplateEngine());
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }

    // this method compute how many elements in arraylist greater the limitCount
    public static int howManyPositiveElements(ArrayList<Number> arrayList, double limitCount, Class className) {
        System.out.println("inside howManyPositiveElements method");

        // if arrayLis is null
        if (arrayList == null) return 0;

        int count = 0;

        // pass on the arrayList, to compute how many positive elements are there
        for (int i = 0; i < arrayList.size(); i++) {

            // if the element is null, no increase
            if(arrayList.get(i) == null){
                continue;
            }
            
            // if the element is not null, increase the count
            if(arrayList.get(i).doubleValue() > limitCount){
                count++;
            }
        }

        // return the count
        return count;
    }
}
