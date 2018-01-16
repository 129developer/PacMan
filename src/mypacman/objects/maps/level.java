/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypacman.objects.maps;

import java.awt.Graphics2D;
import mypacman.utils.Constants;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author bayasys
 */
public class level extends basicMapFunctions implements basicMap {

//    JSONArray ary = new JSONArray("[{\"Type\":\"H\",\"x\":122,\"y\":209,\"height\":0,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":127,\"y\":228,\"height\":40,\"width\":353,\"OBJTYPE\":\"PATH\"},{\"x\":165,\"y\":252,\"OBJTYPE\":\"PM_POSITION\"},{\"x\":222,\"y\":241,\"OBJTYPE\":\"H_GHOST_POSITION\"},{\"x\":280,\"y\":249,\"OBJTYPE\":\"V_GHOST_POSITION\"},{\"x\":333,\"y\":246,\"OBJTYPE\":\"F_GHOST_POSITION\"}]");
    public level() {
        super();
        try {
            this.ary = new JSONArray("[{\"Type\":\"H\",\"x\":41,\"y\":61,\"height\":40,\"width\":1282,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":40,\"y\":62,\"height\":593,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":42,\"y\":616,\"height\":40,\"width\":1266,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1286,\"y\":62,\"height\":594,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":74,\"y\":147,\"height\":40,\"width\":77,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":142,\"y\":94,\"height\":169,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":67,\"y\":234,\"height\":40,\"width\":243,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":270,\"y\":264,\"height\":362,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":67,\"y\":342,\"height\":40,\"width\":205,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":53,\"y\":299,\"height\":40,\"width\":16,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":70,\"y\":294,\"height\":40,\"width\":209,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":100,\"y\":264,\"height\":48,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":157,\"y\":269,\"height\":41,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":219,\"y\":266,\"height\":41,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":69,\"y\":404,\"height\":40,\"width\":95,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":125,\"y\":405,\"height\":120,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":74,\"y\":485,\"height\":40,\"width\":52,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":151,\"y\":455,\"height\":40,\"width\":133,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":187,\"y\":483,\"height\":104,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":68,\"y\":547,\"height\":40,\"width\":141,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":152,\"y\":574,\"height\":47,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":299,\"y\":528,\"height\":40,\"width\":1018,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":357,\"y\":96,\"height\":455,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":461,\"y\":97,\"height\":472,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":563,\"y\":94,\"height\":452,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":664,\"y\":96,\"height\":444,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":760,\"y\":91,\"height\":451,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":852,\"y\":91,\"height\":463,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":774,\"y\":177,\"height\":40,\"width\":86,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":695,\"y\":260,\"height\":40,\"width\":71,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":579,\"y\":366,\"height\":40,\"width\":94,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":476,\"y\":455,\"height\":40,\"width\":97,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":383,\"y\":380,\"height\":40,\"width\":87,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":166,\"y\":147,\"height\":40,\"width\":605,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":973,\"y\":90,\"height\":82,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":883,\"y\":142,\"height\":40,\"width\":129,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":936,\"y\":179,\"height\":104,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":969,\"y\":221,\"height\":40,\"width\":143,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1061,\"y\":97,\"height\":161,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1085,\"y\":140,\"height\":40,\"width\":130,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1185,\"y\":79,\"height\":101,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1111,\"y\":168,\"height\":279,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":875,\"y\":282,\"height\":40,\"width\":243,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1025,\"y\":312,\"height\":164,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":878,\"y\":381,\"height\":40,\"width\":414,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1185,\"y\":174,\"height\":226,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":883,\"y\":475,\"height\":40,\"width\":272,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1052,\"y\":441,\"height\":40,\"width\":5,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1052,\"y\":428,\"height\":40,\"width\":99,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1138,\"y\":476,\"height\":0,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":1124,\"y\":475,\"height\":40,\"width\":163,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":538,\"y\":565,\"height\":54,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":740,\"y\":558,\"height\":59,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":959,\"y\":557,\"height\":60,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":1126,\"y\":557,\"height\":77,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":851,\"y\":556,\"height\":75,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":638,\"y\":564,\"height\":62,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":459,\"y\":563,\"height\":55,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"Type\":\"V\",\"x\":298,\"y\":582,\"height\":40,\"width\":7,\"OBJTYPE\":\"PATH\"},{\"Type\":\"H\",\"x\":299,\"y\":573,\"height\":40,\"width\":170,\"OBJTYPE\":\"PATH\"},{\"x\":147,\"y\":151,\"OBJTYPE\":\"PM_POSITION\"},{\"x\":45,\"y\":621,\"OBJTYPE\":\"H_GHOST_POSITION\"},{\"Type\":\"H\",\"x\":1290,\"y\":76,\"height\":0,\"width\":40,\"OBJTYPE\":\"PATH\"},{\"x\":1291,\"y\":67,\"OBJTYPE\":\"V_GHOST_POSITION\"},{\"x\":569,\"y\":367,\"OBJTYPE\":\"F_GHOST_POSITION\"}]");
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void generateMap(Graphics2D g2d) {
        g2d.setColor(Constants.PATHCOLOR);
//        genPathBoxV(g2d, 274, 120, 200);
//        genPathBoxV(g2d, 94, 140, 20);//con b v
//        genPathBoxV(g2d, 494, 140, 50);//
//        genPathBoxH(g2d, 494, 190, 170);//
//        genPathBoxV(g2d, 660, 190, 150);//
//        genPathBoxH(g2d, 84, 100, 1200);//top hot
//        genPathBoxH(g2d, 84, 300, 1200);
//        genPathBoxH(g2d, 204, 200, 100);
        try {
//            JSONArray ary = new JSONArray("[{\"Type\":\"V\",\"x\":274,\"y\":120,\"height\":200},{\"Type\":\"V\",\"x\":94,\"y\":140,\"height\":20},{\"Type\":\"V\",\"x\":494,\"y\":140,\"height\":50},{\"Type\":\"H\",\"x\":494,\"y\":190,\"width\":170},{\"Type\":\"V\",\"x\":660,\"y\":190,\"height\":150},{\"Type\":\"H\",\"x\":84,\"y\":100,\"width\":1200},{\"Type\":\"H\",\"x\":84,\"y\":300,\"width\":1200},{\"Type\":\"H\",\"x\":204,\"y\":200,\"width\":100},{\"Type\":\"F\",\"x\":80,\"y\":160,\"width\":130,\"height\":120},{\"Type\":\"F\",\"x\":230,\"y\":160,\"width\":130,\"height\":120}]");

//            System.out.println("ary :" + ary.toString());
            generateFromJSON(ary, g2d);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        g2d.setColor(Constants.BLOCKCOLOR);
//        g2d.fillRect(117, 195, 50, 50); //left box inner
//        g2d.fillRect(267, 195, 50, 50); // box 3 inner
    }
//BACKUP
//     JSONObject ob = new JSONObject();
//            ob.put("Type", "V");
//            ob.put("x", 274);
//            ob.put("y", 120);
//            ob.put("height", 200);
//            ary.put(ob);
//
//            ob = new JSONObject();
//            ob.put("Type", "V");
//            ob.put("x", 94);
//            ob.put("y", 140);
//            ob.put("height", 20);
//            ary.put(ob);
//
//            ob = new JSONObject();
//            ob.put("Type", "V");
//            ob.put("x", 494);
//            ob.put("y", 140);
//            ob.put("height", 50);
//            ary.put(ob);
//
//            ob = new JSONObject();
//            ob.put("Type", "H");
//            ob.put("x", 494);
//            ob.put("y", 190);
//            ob.put("width", 170);
//            ary.put(ob);
//
//            ob = new JSONObject();
//            ob.put("Type", "V");
//            ob.put("x", 660);
//            ob.put("y", 190);
//            ob.put("height", 150);
//            ary.put(ob);
//
//            ob = new JSONObject();
//            ob.put("Type", "H");
//            ob.put("x", 84);
//            ob.put("y", 100);
//            ob.put("width", 1200);
//            ary.put(ob);
//
//            ob = new JSONObject();
//            ob.put("Type", "H");
//            ob.put("x", 84);
//            ob.put("y", 300);
//            ob.put("width", 1200);
//            ary.put(ob);
//
//            ob = new JSONObject();
//            ob.put("Type", "H");
//            ob.put("x", 204);
//            ob.put("y", 200);
//            ob.put("width", 100);
//            ary.put(ob);
//
//            ob = new JSONObject();
//            ob.put("Type", "F");
//            ob.put("x", 80);
//            ob.put("y", 160);
//            ob.put("width", 130);
//            ob.put("height", 120);
//            ary.put(ob);
//
//            ob = new JSONObject();
//            ob.put("Type", "F");
//            ob.put("x", 230);
//            ob.put("y", 160);
//            ob.put("width", 130);
//            ob.put("height", 120);
//            ary.put(ob);

}
