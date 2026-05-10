package org.apogames.love.game.love;

public class LoveLevels {

    /**
     *   = nothing
     * . = grass
     * ~ = water
     * * = lava
     * i = ice
     * w = wall
     * y = you
     * p = woman/princess
     * h = heart
     * l = lock
     * k = key
     * t = tree
     * r = rock
     * f = fire
     * s = snowball
     * a = redwall
     * b = bluewall
     * c = graywall
     */
	public static final String MENU = 
            "~~~~~~~~~~~~~~~~~~~~" +
            "~~~.....~~~~.....~~~" +
            "~~.~~....~~....t..~~" +
            "~~..tttttttttt.tt.~~" +
            "~~.tttt.r~r.ttttt.~~" +
            "~~.tTTT.TTT.TTT.t.~~" +
            "~~.ttt...y........~~" +
            "~~~...T.wwwww.h..~~~" +
            "~~~~..T.w...w...~~~~" +
            "~~~~~.T.w.p.w..~~~~~" +
            "~~~~~~..l...w.~~~~~~" +
            "~~~~~~~.wwwww~~~~~~~" +
            "~~~~~~~~....~~~~~~~~" +
            "~~...~...TTT..TTT.~~" +
            "~.ttttttttttttttt..~" +
            "~..ttttttttttt.....~" +
            "~.ttttt.~.ttttt....~" +
            "~..tttt.~~tttt.....~" +
            "~~..ttttttttt.....~~" +
            "~~~~~~~~~~~~~~~~~~~~#" + 
            "hero,4,5,is,5,5,you,6,5," + 
            "love,8,5,is,9,5,key,10,5," +
            "woman,12,5,is,13,5,goal,14,3," +
            "wall,9,13,is,10,13,solid,11,13," +
            "water,6,7,is,6,8,sink,6,9," +
            "rock,14,18,is,15,18,push,16,18," +
            "tree,2,13,is,3,13,solid,4,13," +
            "padlock,14,13,is,15,13,lock,16,13";
	
	public static final String LEVELCHOOSER = 
			"aaa~~~~~~~~~~~~~bbbb" +
    	    "a~~.....TTT......b~b" +
    	    "~~~iiiiiiiiiiiiiibbb" +
    	    "~~...ii.iii...iii.~~" +
    	    "~~................~c" +
    	    "~~....TTT..TTT....~c" +
    	    "~~................~c" +
    	    "~~~..wwwwwwwwww..~~~" +
    	    "~~~......h.......~~~" +
    	    "~~~...y...f..p...~~~" +
    	    "~~~......h.......~~~" +
    	    "~~~..wwwwwwwwww..~~~" +
    	    "~~................~~" +
    	    "~~...tTTTt.TTT....~~" +
    	    "~~...ttttt........~~" +
    	    "~~.ttttttttttt....~~" +
    	    "~~.ttttttttttttt..~~" +
    	    "~~~~~tttttttttt...~~" +
    	    "~~~~....TTT.....~~~~" +
    	    "~~~~~~~~~~~~~~~~~~~~#" + 
    	    "water,8,1,is,9,1,kill,10,1," + 
    	    "ice,13,1,is,14,1,slip,15,1," + 
    	    "hero,6,5,is,7,5,you,8,5," +
    	    "love,11,5,is,12,5,push,13,5," +
    	    "tree,8,18,is,9,18,solid,10,18," +
    	    "wall,6,13,is,7,13,solid,8,13," +
    	    "ice,3,8,is,3,9,melt,3,10," +
    	    "fire,16,8,is,16,9,hot,16,10," +
    	    "fire,19,8,is,19,9,push,19,10," +
    	    "woman,11,13,is,12,13,goal,13,13,";
	
	public static final String EDITOR = 
//			".....tttttttt............t......t............ta.....tt...........tacca..ht...........t...a.ctt...........t.....ct............t......t............tttttttt............ttttt..............tttttt.................ttt............tt..ttt.............ttt.ttt....www......ttt.ttt.............ttt.ttt.............ttt.ttt.............tt..ttt.............tt..ttt.............tt...tt.............tt.tttt...........#IS,10,9,GOAL,11,9,WALL,9,12,IS,10,12,YOU,11,12,#";
//			".....tttttttt............t......t............ta.....tt...........tacca..ht...........t...a.ctt...........t.....ct............t......t............tttttttt............ttttt...........ttttttttt............ty..yttt............ttt.ttt.............ttt.ttt.............ttttttt.............ttttttt.............ttttttt.............ttytttt.............tt.tttt.............tt...tt.............ttytttt...........#HERO,0,0,IS,1,0,YOU,2,0,REDWALL,0,2,IS,1,2,STICKY,2,2,REDWALL,0,3,IS,1,3,PUSH,2,3,GRAYWALL,0,4,IS,1,4,STICKY,2,4,GRAYWALL,0,5,IS,1,5,PUSH,2,5,LOVE,0,6,IS,1,6,GOAL,2,6,TREE,0,8,IS,1,8,SOLID,2,8,GRAYWALL,5,10,IS,5,11,YOU,5,12,REDWALL,4,17,IS,5,18,YOU,6,18,#";
//			".....tttttttt............t......t............ta.cccctt...........tacca.cht...........t...a.ctt...........t.....ct............t......t............tttttttt............ttttt...........ttttttttt......y.....ttt.yttt............ttt.ttt.....iiii....ttt.ttt.............ttttttt.............ttttttt.............ttttttt.............ttttttt.............ttttttt.............tt...tt.............ttytttt...........#HERO,0,0,IS,1,0,YOU,2,0,REDWALL,0,2,IS,1,2,STICKY,2,2,GRAYWALL,0,4,IS,1,4,STICKY,2,4,LOVE,0,6,IS,1,6,GOAL,2,6,TREE,0,8,IS,1,8,SOLID,2,8,GRAYWALL,5,10,ROCK,16,10,IS,5,11,ICE,9,11,IS,10,11,SLIP,11,11,YOU,5,12,REDWALL,4,18,IS,5,18,YOU,6,18,#";
//
//			
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "...................." +
            "....................#";
	
	public static final String OPTIONS = 
            "...................." +
            "ttt~~~iiih***t..twww" +
            "t.t~.~.i.h*.*tt.tw.." +
            "t.t~~~.i.h*.*ttttwww" +
            "t.t~...i.h*.*t.tt..w" +
            "t.t~...i.h*.*t..t..w" +
            "ttt~...i.h***t..twww" +
            "...................." +
            "...................." +
            ".y................p." +
            "...................." +
            "...................." +
            "tttttttttttttttttttt" +
            "t~ttt~~~t.~ttt~t~~~t" +
            "t~ttt~t~t.~ttt~t~ttt" +
            "t~ttt~t~tt~~t~~t~~~t" +
            "t~ttt~t~ttt~t~tt~ttt" +
            "t~ttt~t~ttt~~~tt~ttt" +
            "t~~~t~~~tttt~ttt~~~t" +
            "tttttttttttttttttttt#";
	
    public static final String[] LEVELS = new String[] {
					".TTT.TTT....TTT.TTT." +
					"~~~~~~~~~~~~~~~~~~~~" +
					"~~~~~hhhh~hhhh~~~~~~" +
					"~~~~h....h....h~~~~~" +
					"~~~~h.........h~~~~~" +
					"~~~~~h..TTT..h~~~~~~" +
					"~~~~~~h.....h~~~~~~~" +
					"~~~~~~~h.y.h~~~~~~~~" +
					"~~~~~~~~h.h~~~~~~~~~" +
					"~~~~~~~~.h.~~~~~~~~~" +
					"~~~~~~~~t.t~~~~~~~~~" +
					"~~~~~~~t...t~~~~~~~~" +
					"~~~~~~t.....t~~~~~~~" +
					"~~~~~t.......t~~~~~~" +
					"~~~~t....p....t~~~~~" +
					"~~~~t....t....t~~~~~" +
					"~~~~~tttt~tttt~~~~~~" +
					"~~~~~~~~~~~~~~~~~~~~" +
					"~~~~~~~~~~~~~~~~~~~~" +
					"~~~~~~~~~~~~~~~~~~~~#" + 
		            "water,1,0,is,2,0,sink,3,0," + 
		            "hero,5,0,is,6,0,you,7,0," +
		            "tree,16,0,is,17,0,solid,18,0," +
		            "love,8,5,is,9,5,kill,10,5," +
		            "woman,12,0,is,13,0,goal,14,0,",
    		
            		"~~~~~~~~~~~~~~~~~~~~" +
            		"~~~~tttttttt~~~tt~~~" +
            		"~~tttttttttttt~ttt~~" +
            		"~~tt~ttttt..tt~~~~~~" +
            		"~~~~~t.TTT.y.tttttt~" +
            		"~~~~tt......ttttt.~~" +
            		"~~tttttttttttt....~~" +
            		"~~~.ttt..ttt..TTT~~~" +
            		"~~~.TTT.......T..~~~" +
            		"~~~...hhhhhh..T..~~~" +
            		"~~~..h......h....~~~" +
            		"~~~..h..p...h....~~~" +
            		"~~...h......h.....~~" +
            		"~~....hhhhhh......~~" +
            		"~~..tt............~~" +
            		"~~.tttt.tttttt....~~" +
            		"~~.tttttttTTTttt..~~" +
            		"~~...tttttttttt...~~" +
            		"~~~.............~~~~" +
            		"~~~~~~~~~~~~~~~~~~~~#" + 
            		"water,16,19,is,17,19,sink,18,19," + 
            		"hero,4,8,is,5,8,you,6,8," +
            		"tree,7,4,is,8,4,solid,9,4," +
            		"love,14,7,is,15,7,kill,16,7," +
            		//"love,14,7,is,14,8,solid,14,9," +
	            	"woman,10,16,is,11,16,goal,12,16,",
     	            
	                "tttttt.....tttttttt~" +
	                "tttttttttttttt.....~" +
	                ".....ttttt~~~tttt~~~" +
	                "ttttttt~~~~~~~tt~~~~" +
	                "tttt~~~~~~~~~~~~~~~~" +
	                "~~~~~~~~~~~~~~~~~~~~" +
                    "~~~.............~~~~" +
                    "~~..........www...~~" +
                    "~~......f..w...w..~~" +
                    "~~.........w.p.w..~~" +
                    "~~....y....w...w..~~" +
                    "~~~.........www...~~" +
                    "~~~~..............~~" +
                    "~~~~~~~.....~~~~~~~~" +
                    "~~~~~~~~..~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~" +
                    "~~~~~~~~~~~~~~~~~~~~#" +
                    "water,7,0,is,8,0,kill,9,0," +
                    "hero,5,7,is,6,7,you,7,7," +
                    "woman,1,2,is,2,2,goal,3,2," +
                    "wall,14,1,is,15,1,solid,16,1," +
                    "wall,16,8,is,16,9,kill,16,10,",
	            	
    	            "...ttt........tt...." +
       	            "ttttttttttttttttt~~~" +
       	            "~ttttt......tt~~tt~~" +
       	            "~ttttt....T.tt~~~~~t" +
       	            "~~tttt..y.T.tttttttt" +
       	            "~~~ttt....T.ttttt.tt" +
       	            "~~tttt.TTT..tt....tt" +
       	            "~~~..t.....tt....ttt" +
       	            "~~~..ttt..ttt....ttt" +
       	            "~~~....tttt...r..ttt" +
       	            "~~~..r............tt" +
       	            "~~~...............tt" +
       	            "~~~~~~~~~~~~~~~~~~tt" +
       	            "tt~~~~~~~~~~......~~" +
       	            "tt..~~~~~~~...p...~~" +
       	            "tt.....~~~~......~~~" +
       	            "ttt.h.f.~~~...r...~~" +
       	            "tttt...tt~~~......~~" +
       	            "~ttttttttt~~~~~~~~~~" +
       	            "ttttttttttt~~~~~~~~~#" + 
       	            "water,16,0,is,17,0,sink,18,0," + 
       	            "hero,7,6,is,8,6,you,9,6," +
       	            "tree,0,0,is,1,0,solid,2,0," +
       	            "love,10,0,is,11,0,goal,12,0," +
       	            "rock,11,15,is,16,14,push,15,16," +
       	            "woman,10,3,is,10,4,kill,10,5,",
    	            
    	            "...................." +
                    "tttttttttttttttttttt" +
                    "ttttt....t....tttttt" +
                    "tttt...........t...t" +
                    "tttt...........l.p.t" +
                    "ttttt.r.....r.tt...t" +
                    "tttttt.......ttttttt" +
                    "tt................tt" +
                    "tt.ttttt.y.tttttt.tt" +
                    "t......tthttt......t" +
                    "t..................t" +
                    "t......t...tt......t" +
                    "tttttttt...ttttttttt" +
                    "t~ttt~~~t.~ttt~t~~~t" +
                    "t~ttt~t~t.~ttt~t~ttt" +
                    "t~ttt~t~tt~~t~~t~~~t" +
                    "t~ttt~t~ttt~t~tt~ttt" +
                    "t~ttt~t~ttt~~~tt~ttt" +
                    "t~~~t~~~tttt~ttt~~~t" +
                    "tttttttttttttttttttt#" + 
                    "hero,5,3,is,6,3,you,7,3," + 
                    "love,15,10,is,16,10,key,17,10," +
                    "woman,11,3,is,12,3,goal,13,3," +
                    "empty,9,13," +
                    "water,0,0,is,1,0,sink,2,0," +
                    "rock,2,10,is,3,10,push,4,10," +
                    "tree,4,0,is,5,0,solid,6,0," +
                    "padlock,14,0,is,15,0,lock,16,0,", 
    	    		
    	    		MENU, 

    	            "...t~~~~~~ttt~~~~~~~" +
       	            "...t~~~tttttttt~~~~~" +
       	            "..tt~~tt......tt~~~~" +
       	            "ttt~~~tttttttttt~~~~" +
       	            "~~~~~ttt......tttt~~" +
       	            "~~~~tttt..TTT.ttt~~~" +
       	            "~~tttttt......tt~~~~" +
       	            "~ttttttt.p.rr.ttt~~~" +
       	            "~ttttttt......tttt~~" +
       	            "~~ttttttt~~~~tttttt~" +
       	            "~~ttt.........tttt~~" +
       	            "~~ttt..f........ttt~" +
       	            "~~tt..............t~" +
       	            "~~ttttt......T...tt~" +
       	            "~ttttttt~~~..T..ttt~" +
       	            "~~tttttt~~~..T..ttt~" +
       	            "~ttttttty~~....tttt~" +
       	            "~tttttttttttttttt~t~" +
       	            "~~~~~ttttttttttt~~t~" +
       	            "~~~~~~~~~~~~~~~~~~~~#" + 
       	            "water,8,2,is,9,2,sink,10,2," + 
       	            "hero,13,13,is,13,14,goal,13,15," +
       	            "rock,10,5,is,11,5,push,12,5," +
       	            "tree,1,0,is,1,1,solid,1,2," +
       	            "woman,0,0,is,0,1,you,0,2,",
       	            
       	            "tttttttttttttt.~~baattttttttttttttt~~bbattttttttttttttt.~~bbtttttt...tttttt..~~btttttt.rptttttt..~~btttttt.rrtttutt..~~~tttttttt.tttutt...~~t...tttt....utt...~~t...ttt...t.utt...~~t...ttt...ttttt...~~t...ttttttttttt...~~t...tttttttttt....~~t...tttttttt.....~~~t...tttttt......~~~~t..ttttt.......~~~~~t..ttt......~~~~..~~ttttt.....~~~bbbc..~ttttt....~~~.bfbc.h.tttt.~~~~~...bbbc...tttt.~~~~~...b..c...#WOMAN,1,7,IS,2,7,YOU,3,7,STAR,1,9,IS,2,9,ALLGOAL,3,9,TREE,1,11,IS,2,11,SOLID,3,11,ROCK,1,13,IS,2,13,PUSH,3,13,IS,1,14,WINABLE,1,15,#",

       	            "~~ttttt.....~~....tt" +
    	            "~~~ttt.....~~~..tttt" +
       	            "~~ttt~~~~~~~~~~~~~tt" +
       	            "~~~~~~~ttttttttt~~~t" +
       	            "ttt~~~~ty......t~~~t" +
       	            "tttt~ttt..h..T.t~~tt" +
       	            "ttt~~ttthhh..T.tt~~t" +
       	            "tt~~~~tt.....T.ttt~~" +
       	            "tt~ttttt.......tttt~" +
       	            "~~~~tttt.TTT...ttt~~" +
       	            "~ttttttt.......tttt~" +
       	            "~ttttttttttttttttt~~" +
       	            "~~~~~tttttttttttt~~~" +
       	            "~~~~~~~~~~~~~~~~~~~~" +
       	            "~~~~~~~~~~~~~~~~~~~~" +
       	            "~~~~~~~~~~~~~~~~~~~~" +
       	            "~tt~tttttttttttt~~~~" +
       	            "~tt..........ttttt~~" +
       	            "~.....r.~~......ttt~" +
       	            "~~.tttt....r.tt....~#" + 
       	            "tree,12,5,is,12,6,solid,12,7," + 
       	            "hero,3,17,is,4,17,you,5,17," +
       	            "tree,10,17,is,11,17,kill,12,17," +
       	            "love,9,9,is,10,9,solid,11,9," +
       	            "woman,6,14,is,7,14,goal,8,14,",
       	            
       	            "...t...t...tttttt~tttttttttttttt...tt~~t...tttttttt..y..tt~tttttttttttt.....tt~t...tttttttt..ttttt~t.ttt.p.b.~~~tttttt~t.ttt..b..~~~tttttt~ttttt.bab.~~~ttttt~~t...ttttttttttttt~~~t.ttttttttttttt~~~~~t.ttttttttttttt~~t~ttttttttttttttt~~~t~tt~~ttt..tttt~~~tt~~tt~~tt..f.tt~~~~tt~~ttt~~~.....~~~~...~~tttt~~~~~~~~~...w..~tttttt~~~~~...www..~~ttttt........w....~~~tttt.r..f...w.....~~ttttt.............~~#WOMAN,0,0,IS,1,0,YOU,2,0,WATER,4,0,IS,5,0,SINK,6,0,TREE,8,0,IS,9,0,SOLID,10,0,HERO,0,2,IS,1,2,GOAL,2,2,BLUEWALL,0,4,IS,1,4,STICKY,2,4,IS,0,5,PUSH,0,6,REDWALL,0,8,IS,1,8,STICKY,2,8,IS,0,9,PUSH,0,10,#",

					//11
       	            "...................." +
       	            "                    " +
       	            "     tttttttttttt   " +
       	            "    tt.t....~~..ttt " +
       	            "   ttp.t....~~.T..t " +
       	            "  ttttlt.....~~....t" +
       	            " tt..........~~~~~tt" +
       	            "t..wwwww......~~~..t" +
       	            "tt.....ww.~~~~~~~~~t" +
       	            "tt.TTT..w..........t" +
       	            "t.......w........T.t" +
       	            "tt.y...ww........T.t" +
       	            "tttwwwww.......r.T.t" +
       	            "tttt.......h.......t" +
       	            "ttttt..TTT.......ttt" +
       	            " tttttttttttttttttt " +
       	            "                    " +
       	            "                    " +
       	            "                    " +
       	            "....................#" +
       	            "water,17,10,is,17,11,sink,17,12," + 
       	            "hero,3,9,is,4,9,you,5,9," +
       	            "tree,8,19,is,9,19,solid,10,19," +
       	            "wall,7,14,is,8,14,solid,9,14," +
       	            "woman,1,0,is,2,0,goal,3,0," +
       	            "padlock,16,0,is,17,0,lock,18,0," +
       	            "rock,6,9,love,3,10,key,15,4,rock,9,4,",
       	            
       	            "~~~...~...~~bbbbbbbb~~~.~~~~~~~~biiiiiib~~~.~~~...~~biipiyib~~~~~~~~~~~~bbiihiib~~~...~...~~~bbyipib~~~~~~~~~~~~~~bbiiib~~~~~~.wwwwww~~bbiib~~~~~~.wiiiiww~~bbbb~~~~~.wwtwwfiw~~~~~~~~~~~.wittfiiw~~~~~~~~~~~.wiiwfiiw~aaaaa~~~~~.wiiuiwww~a***a~~~~~.wwwiww~~~a***a~~~~~...witw~~~a***a~~~~~...wwww~~aa***a~~~~~~......aaa****a~~~~~~~.....a******a~~~~~~~~....a******a~~~~~~~~~...aa***aaa~~~~~~~~~~...aaaaa..#FIRE,3,0,IS,4,0,PUSH,5,0,STAR,7,0,IS,8,0,YOU,9,0,IS,3,1,WINABLE,3,2,WALL,7,2,IS,8,2,SOLID,9,2,TREE,3,4,IS,4,4,ALLGOAL,5,4,BLUEWALL,7,4,IS,8,4,SOLID,9,4,#~~~...~...~~iiiiiiii~~~.~~~~~~~~iiiiiiii~~~.~~~...~~iiiiiiii~~~~~~~~~~~~iiiiiiii~~~...~...~~~iiiiiii~~~~~~~~~~~~~~iiiiii~~~~~~.iiiiii~~iiiii~~~~~~.iiiiiii~~iiii~~~~~.iiiiiiii~~~~~~~~~~~.iiiiiiii~~~~~~~~~~~.iiiiiiii~*****~~~~~.iiiiiiii~*****~~~~~.iiiiii~~~*****~~~~~...iiii~~~*****~~~~~...iiii~~.*****~~~~~~......********~~~~~~~.....********~~~~~~~~....********~~~~~~~~~...********~~~~~~~~~~...*****..#",
       	            
       	            "~~tttttttttttttttttt" +
       	            "~tttttt.TTT......ttt" +
       	            "ttttt..............t" +
       	            "ttt.........wwwww.tt" +
       	            "tt.....y....w...w..t" +
       	            "t...........w.p.l.tt" +
       	            "tt..........w...w..t" +
       	            "tttt........wwwww..t" +
       	            "ttt...............tt" +
       	            "t......~~~~~~~~~...t" +
       	            "tt.T...~~~~~~~~~...t" +
       	            "tt.T...~~T~~~T~~...t" +
       	            "t..T...~~~~~~~~~..Tt" +
       	            "tt.....~~~~~~~~~..Tt" +
       	            "ttt...............Tt" +
       	            "tttt...............t" +
       	            "ttttttttt..TTT...ttt" +
       	            "tttttttttttttttttttt" +
       	            "~~ttttttTTTttttttt~~" +
       	            "~~~~ttt.....ttt~~~~~#" + 
       	            "water,3,10,is,3,11,sink,3,12," + 
       	            "hero,11,16,is,12,16,you,13,16," +
       	            "tree,8,18,is,9,18,solid,10,18," +
       	            "wall,18,12,is,18,13,solid,18,14," +
       	            "woman,8,1,is,9,1,goal,10,1," +
       	            "padlock,4,15,is,5,15,lock,6,15," +
       	            "empty,9,11,key,13,11,",
       	            
       	            "....................~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~.aa...www....~~~~~~~.attttttttaa..~~~~~..atttttttt.a..~~~~~.tttta.ahttt...~~~~~ttttt.a.atttaa.~~~~~ttttta.a.tttta.~~~~~~~ttt.y..tttta.~~~~tt~~tttttttttt..~~~~ttt~~tttttttt...~~~~ttt~~~ttttttww..~~~~ttttt~~..bb..ww.~~~~tttttt~~~~....w~~~~~ttttttt~~~~...~~~~~..cccttttt~~~~~~~~~~b...ctttttt~~~~~~~~bb.u.ccctttt~~~~~~~ccf......tttt.......c..#HERO,0,0,IS,1,0,YOU,2,0,TREE,4,0,IS,5,0,SOLID,6,0,LOVE,8,0,IS,9,0,GOAL,10,0,REDWALL,12,0,IS,13,0,PUSH,14,0,REDWALL,16,0,IS,17,0,STICKY,18,0,#",
       	            
       	            "iiiiiiiiiiiiiiiiiiii" +
       	            "iiiiiii..iiiiiiiiiii" +
       	            "iiiii...iiii..iiiiii" +
       	            "iii.............iiii" +
   	                "ii................ii" +
                    "i..................." +
                    "ii.................t" +
                    "iii...f.....y.tttttt" +
                    "ii.............ttt.t" +
                    "t.........tttt.ttt.t" +
                    "t.......ttt........t" +
                    "tt...tttt.......t..t" +
                    "tt............tttttt" +
                    "ttlttttttltt..tttttt" +
                    "tt.rtt~~~~~~tttttttt" +
                    "~~~tttt~~p~~tttttttt" +
                    "~~~ttt~~~~~~~~~tttt~" +
                    "~~~tttt~~~~~tttttt~~" +
                    "~~~~tttttttttttt~~~~" +
                    "~~~~~ttttttttt~~~~~~#" + 
                    "padlock,0,19,is,1,19,lock,2,19," +
                    "tree,17,18,is,18,18,solid,19,18," +
                    "hero,0,0,is,1,0,you,2,0," +
                    "push,2,11,is,17,5,fire,2,4," +
                    "woman,8,7,is,9,7,hot,10,7," +
                    "ice,17,0,is,18,0,melt,19,0," +
                    "ice,9,0,is,10,0,kill,11,0," +
                    "goal,10,11,water,18,8,is,6,9," +
                    "rock,11,10,key,6,3,",
                    
                    "*****tttttt.f.ttt*******tttttttt..ttt******tttttttttt.tt*******ttttttttttttt*******tttstitttttt********tttihiittttt********tttiiiittttt********tttstpttttt**********tttttttttt**********ttttttt**************ttttt*********.******ttt*********..***************..aaa*...*...*...**...aba*************bbbbbba*...*...*...*bwwwaaa*************bbbwcac*...*...*****cccwccc*************cwwwcu.*************ccccc..#WOMAN,1,13,IS,2,13,YOU,3,13,ICE,5,13,IS,6,13,SLIP,7,13,WOMAN,9,13,IS,10,13,KEY,11,13,SNOWBALL,1,15,IS,2,15,YOU,3,15,LOVE,5,15,IS,6,15,GOAL,7,15,LOVE,9,15,IS,10,15,LOCK,11,15,LAVA,1,17,IS,2,17,SOLID,3,17,TREE,5,17,IS,6,17,SOLID,7,17,#*****............*******.............******.............*******.............*******...i.i......********...iiii.....********...iiii.....********...i.i.....**********..........**********.......**************.....*********.******...*********..***************.....*...*...*...**......*************.......*...*...*...*.......*************.......*...*...*****.......*************.......*************.......#",
                    
                    "....................ttttttttttttttttt~~~...ttttttttttttttt~~ttttttttttttttttttt~t...~~~..iiiiittttttt.w.~~~..iiii..tttttt.w..~~..iii...tttttt.w...~..iii...tttttt.wp.....tt...ttttttt.....~~~tt.h.ttttttt.....~~.ttt.tttttttt.....~..ttttttttttttt....~..tttttttttt~ttt...~..ttttttttt~~tttt..ttttttttttt~~~~ttttttttttttttt~~~~~ttttttttttttt~~~~~~~~ttttttt~~~~~~~~~...~~~~~~~~~~~~~........~~~~~~~~..........#WOMAN,0,0,IS,1,0,YOU,2,0,LOVE,4,0,IS,5,0,GOAL,6,0,WATER,8,0,IS,9,0,SINK,10,0,TREE,12,0,IS,13,0,SOLID,14,0,ICE,16,0,IS,17,0,KILL,18,0,WALL,0,2,IS,1,2,PUSH,2,2,WALL,2,9,IS,2,10,IS,4,10,STICKY,2,11,HOT,8,12,ICE,5,13,#",
                    
                    "tttttttttttttttttttt" +
                    "ttt**************ttt" +
                    "tt......*******....." +
                    "t***********tt***..." +
                    "tt.***....*.ttt*...." +
                    "t.........*..ht.w..t" +
                    "tt....TTT.*..tt....t" +
                    "tt........******...t" +
                    "tt........*..*..r..t" +
                    "tt.tt....***........" +
                    "tt..t.....******...." +
                    "t..tt..t..**........" +
                    "t..ty.tt.***........" +
                    "tt.ttttt..**........" +
                    "tt.t~tt...*.....~~~~" +
                    "tt.t~t....*.....~..." +
                    "t........***...~~..." +
                    "tttt.....****..~~..." +
                    "tttttttttttttttttttt" +
                    "tttttttttttttttttttt#" +
                    "wall,2,13,is,2,14,solid,2,15," +
                    "hero,6,6,is,7,6,you,8,6," +
                    "lava,3,2,is,4,2,hot,5,2," +
                    "water,19,2,is,19,3,sink,19,4," +
                    "hero,19,10,is,19,11,melt,19,12," +
                    "empty,14,17,goal,19,17,rock,12,7," +
                    "tree,4,17,is,5,17,solid,6,17," +
                    "love,13,11,is,14,11,push,15,11,",
                    
                    "tttttttttttttttttttttttt...........ttttttttt...........ttttttttt......t....tttttttttttttttttttttttttttttt........b.ttttttttttww......bbttttttttttywbb....wwtttttttttt..b.....wpttttttttttttttttttttttttt~~~~ttt...ttt~~~~~~~~~~~tt.u.f.tt~~~~~~~~~~~t.......t~~~~~~~~~~~t...w...t~~~~~~~~~~~~.......~~~~~~~~~~~~~.a...a.~~~~~~~~~~~~~..aaa..~~~~~~~~~~~~~.......~~~~~~~~~~~~~~.....~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#HERO,4,1,IS,5,1,YOU,6,1,WALL,8,1,IS,9,1,STICKY,10,1,BLUEWALL,12,1,IS,13,1,STICKY,14,1,TREE,4,2,IS,5,2,SOLID,6,2,WALL,8,2,IS,9,2,PUSH,10,2,BLUEWALL,12,2,IS,13,2,PUSH,14,2,WOMAN,4,3,IS,5,3,GOAL,6,3,#",
                    
                    "tttttttttt.y.ttttttt" +
                    "t...liiiiriiii~~~~~t" +
                    "t.h.liswiiiiisiiirit" +
                    "t...liiiiirisrisiiit" +
                    "tlllliiiiiiwiiiiiiit" +
                    "tiiiiiiiiiiiiiiiiirt" +
                    "ti...iiiiiiiwiisii~t" +
                    "ti.i.iiiiriiiiiiii~t" +
                    "tiiii~...~~~iwiiiiit" +
                    "tiiii~.....~iiiiw~~t" +
                    "twiii~.....~iiiiriit" +
                    "tiiwi~.....~iiiiiirt" +
                    "tsiii~~~~~~~iiwiiiit" +
                    "tiiiiiiwiiriiiiiiiit" +
                    "tiiiiiiiisiiitt...tt" +
                    "t.....wiiisiit.....t" +
                    "t.....iiwiiiit.....t" +
                    "tttt~~isiiiiit.....t" +
                    "...ttttttttt~ttttttt" +
                    "....................#" +
                    "hero,4,19,is,5,19,you,6,19," +
                    "ice,8,19,is,9,19,slip,10,19," +
                    "love,12,19,is,13,19,goal,14,19," +
                    "tree,0,19,is,1,19,solid,2,19," +
                    "padlock,0,18,is,1,18,lock,2,18," +
                    "key,4,15,is,3,6,hero,2,6," +
                    "water,16,19,is,17,19,kill,18,19," +
                    "wall,7,10,is,8,9,solid,9,10," +
                    "snowball,1,16,is,2,15,solid,3,16," +
                    "rock,15,16,is,16,16,solid,17,16#"+
                    "tttttttttt.y.ttttttt" +
                    "tiiiiiiiiiiiiiiiiiit" +
                    "ti.iiiiiiiiiiiiiiiit" +
                    "tiiiiiiiiiiiiiiiiiit" +
                    "tiiiiiiiiiiiiiiiiiit" +
                    "tiiiiiiiiiiiiiiiiiit" +
                    "ti...iiiiiiiiiiiiiit" +
                    "ti.i.iiiiiiiiiiiiiit" +
                    "tiiii~...~~~iiiiiiit" +
                    "tiiii~.....~iiiiiiit" +
                    "tiiii~.....~iiiiiiit" +
                    "tiiii~.....~iiiiiiit" +
                    "tiiii~~~~~~~iiiiiiit" +
                    "tiiiiiiiiiiiiiiiiiit" +
                    "tiiiiiiiiiiiitt...tt" +
                    "t....iiiiiiiit.....t" +
                    "t....iiiiiiiit.....t" +
                    "ttttiiiiiiiiit.....t" +
                    "...ttttttttt~ttttttt" +
                    "....................#",

					//21
					"ttttttttttiii***t.t..t.ttt..tttii***t.t..t.tth..tttii***t.t..t.tt..w.tiii***tttttt.ttw...tiii***tttttt.t.....iiii***..tt.........iiii***..tt.w...w.y.iiii***.p.t.........iiii***...t.........iiii***...t........iiiii**.ttttw.w....iiiiii**ttttt......iiiiiii**ttttt.....iiiiiiii**ttttt...iiiiiiiiii**tttttiiiiiiiiiiii***tttttiiiiiiiiiii****tttttiiiiiiiiiii***ttttttiiiiiiiii*****tttttt...iiiii*****tt.....#HERO,17,0,WOMAN,19,0,TREE,0,1,IF,2,1,IS,17,1,IS,19,1,IS,0,2,HERO,2,2,YOU,17,2,GOAL,19,2,SOLID,0,3,ISON,2,3,LOVE,2,4,THEN,2,5,LAVA,2,6,IS,2,7,HOT,2,8,ICE,9,8,ICE,0,19,IS,1,19,KILL,2,19,WALL,15,19,IS,16,19,STICKY,17,19,AND,18,19,PUSH,19,19,#",

					".t...tt...ttttt..ttttttttttttttt.....*tt.ttttt.......iii***t.tt......tttiiii****.tt.p...tttti.ii****ttt.......ttiiii****ttt....t..tttiii****ttt...tt.tttt.ii****.tt...ttitttt.ii****...iitttitttttt*****...iitttittttttt****.w.iiiiiiitttttttttt..iiiiiiiitt.tttttttttiiiiiiiitt.tttttttttiiiiiiiitt.tttttttttiiiiiiiittttttttttttttttitttttttttttttttttttitttttttt~~~~~tttttthttttttt~~~~~~ttttttttttttt~~~~~~~#LOVE,2,0,IS,3,0,GOAL,4,0,TREE,7,0,IS,8,0,SOLID,9,0,ICE,0,2,IS,0,3,WOMAN,3,3,IS,4,3,YOU,5,3,SLIP,0,4,PUSH,13,4,WALL,4,6,SLIP,5,6,ISNOT,8,6,IS,13,7,LAVA,12,12,IS,12,13,KILL,12,14,#",

					"...ttttttttttttttttttttttttt...tttttttttttttttt......ttttttttttttt.........ttttttttttt....y.....ttttttttt............tttttttt............ttttttt....~~~~~...~~tttttt....~~..~...~~tttttt....~...~...~~~ttt......~.~~~....~~~t................~~~t................~~~t.............ttt~~tt.h.u......tttttttttt.......tttttttttttttt....tttttttttttttttttttttttttttttttttttttttttttttttt~~~~~~tttttttttttttt~...tt#TREE,0,0,IS,1,0,SOLID,2,0,HERO,7,6,IS,7,7,YOU,7,8,GOAL,10,9,LOVE,2,13,AND,3,13,STAR,4,13,WATER,15,19,IS,16,19,SINK,17,19,#",

					"...***********......********..*****.....***ttttttt*****.~~~.**tttttttt.***.~~.~~*tttt...tt.***.~.h.~ttttt.y.tt.***.~~.~~ttttt...tt.***..~~~.~ttttt.ttt.*****....~~.........*****....~~........**.***....~~~.......******....~~~.......******....~~~...........**....~~~..t..tttt..**....~~~..t.......t**....~~~~.ttttttt..**....~~~~..tttttt..***...~~~~....tttt...**...~~~~~..........***.....~~~.........**...#LOVE,0,0,IS,1,0,GOAL,2,0,WATER,17,0,IS,18,0,SINK,19,0,TREE,12,9,HERO,8,14,IS,9,14,YOU,10,14,AND,11,14,MELT,12,14,LAVA,0,19,IS,1,19,HOT,2,19,TREE,17,19,IS,18,19,SOLID,19,19,#",

					"tttttttttttttttttttttttt...ttttttttttttttttttttt........tttt...t...t........tttt...t...ttt......tt...f.t...hhh.......r.p...t...ttt......tt.....t...t........ttttt..tt.tt...ru...ttttt.y.t.tt........ttttt...t.tt........ttttt...tttttttttttttttttttttttttttttt...ttttttttttttttttttttttt~~~~~..~~~~.........~~~~~~~~~~~~~~~~~......~~~~~~~~~~~~~~~~~..~~~~.www.~~~~~~~~~~~~~~~.wbww.aa~~~~~~~~~~~~.wbbwbba..~~~~#LOVE,4,1,IS,5,1,SINK,6,1,STAR,11,4,IS,12,4,SINK,13,4,FIRE,5,5,ROCK,11,6,IS,12,6,PUSH,13,6,WOMAN,5,8,AND,13,8,IS,5,9,YOU,5,10,HERO,1,11,IS,2,11,GOAL,3,11,TREE,14,12,IS,15,12,SOLID,16,12,#",

					"t...t...t...ttttttttttttttttttttttttttttttt............tttttt.............tt~~~t~.y..........tt~~~ht~.......f....t~~~~~t~~...........t~~p~~t~~...........t~~~~~t~............ttt~~tt.....s.......ttttttt..........t..ttttttt~.........tttttttttt~~........tttttttttt~~~..tttt..ttttttttt~~t.........tttttttt~ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt#LOVE,1,0,IS,2,0,GOAL,3,0,HERO,5,0,IS,6,0,YOU,7,0,TREE,9,0,IS,10,0,SOLID,11,0,WOMAN,7,3,IF,11,3,IS,5,5,FIRE,4,7,THEN,7,7,SNOWBALL,2,9,YOU,12,10,IS,7,11,WATER,4,14,IS,5,14,SINK,6,14,#............................................................................~~~.~..............~~~~.~.............~~~~~.~~............~~~~~.~~............~~~~~.~...............~~..........................................~...................~~..................~~~.................~~..................~...................................................................................................#",

					"ttt               iittt ............. iittt ............. iittt ............. iittt ............. iittt ............. iittt ...wwwwwwww.. iittt ......rr..... .ittt ....y.rr.p... ..ttt ......rr..... f.ttt ...wwwwwwww.. ..ttt ............. .*ttt ............. **ttt ............. **ttt               **~~~~~~~hbbhbhbhhhb**~~~~~~~hhbhbhbhbhb**~~~~~~~~bbhbhbbbhb**~~~~~~~~~bhbhhhbhb**~~~~~~~~bbabhhhbhb**#HERO,4,1,IS,5,1,GHOST,6,1,HERO,7,4,IS,8,4,YOU,9,4,WOMAN,12,4,IS,13,4,GOAL,14,4,WALL,7,12,IS,8,12,SOLID,9,12,ROCK,12,12,IS,13,12,PUSH,14,12,#",

					"...t...ttttttttttttttttttttttttttttttttttttttttttttttttttttttttt                tttt ........~..h...tttt ........~..h.p.tttt ........~..h...tttt ........~...hhhtttt .y......~......tttt ........~......tttt ........~......tttt ........~......tttt                ttttttttttttttttttttttttttttttttttttt..f~~~~~~~~~~~~~~~~....tt~~~~~~~~~~~~~~~~~.ttt~~~~~~~~~~~~~~~~~~~~~~~......~~~~~~~~~~~~~............~~~#TREE,0,0,IS,1,0,SOLID,2,0,TREE,4,0,IS,5,0,HOT,6,0,WATER,5,4,IS,6,4,HOT,7,4,WOMAN,17,4,IS,18,4,GOAL,19,4,HERO,9,6,IS,9,7,YOU,9,8,AND,9,9,GHOST,9,10,HERO,5,11,IS,6,11,MELT,7,11,LOVE,15,11,IS,16,11,KILL,17,11,AND,18,11,GHOST,19,11,#",

					".t.t.t.*.*.*.*.tt.t..t.t.t.*.*.*.*.tt.t..t.t.t.*.*.*.*.tt.t..t.t.t.********ttttt.t.t.t.*******.tttt..t.t.t.******..tttt..t.t.t.***ttttttttt..t.t.t.***tttiihtptttttttt****ttiiii~~t.tttttt***.ttyuiittt.tttt*****.ttiiiittt.ttt******.tttttttttttt******..ttttttttt.tt*****...........~.t*****..f........~~.******....~~~~~~~~~~****.....~~~~~~~~~~.****....~~~~~~~~~~~.***.....~~~~~~~~~~~.***...~~~~~~~~~~~~~~#IF,0,0,IF,2,0,IF,4,0,IF,6,0,GRAS,8,0,WOMAN,10,0,ROCK,12,0,HERO,14,0,HERO,17,0,TREE,19,0,HERO,0,1,HERO,2,1,HERO,4,1,HERO,6,1,IS,8,1,IS,10,1,IS,12,1,ISNOT,14,1,IS,17,1,IS,19,1,ISON,0,2,ISON,2,2,ISON,4,2,ISON,6,2,SLIP,8,2,ALLGOAL,10,2,WINABLE,12,2,SLIP,14,2,YOU,17,2,SOLID,19,2,LOVE,0,3,LOVE,2,3,LOVE,4,3,LOVE,6,3,THEN,0,4,THEN,2,4,THEN,4,4,THEN,6,4,HERO,19,4,WATER,0,5,STAR,2,5,LOVE,4,5,ICE,6,5,IS,19,5,IS,0,6,IS,2,6,IS,4,6,IS,6,6,HOT,19,6,GRAS,0,7,ROCK,2,7,WOMAN,4,7,TREE,6,7,ICE,19,8,IS,19,9,MELT,19,10,WATER,19,12,IS,19,13,SOLID,19,14,ROCK,19,16,IS,19,17,PUSH,19,18,#.......*.*.*.*.............*.*.*.*.............*.*.*.*.............********............*******.............******..............***.................***...ii..~........****..iiii~~........***...~iii........*****...iiii.......******.............******..............*****...........~..*****...........~~.******....~~~~~~~~~~****.....~~~~~~~~~~.****....~~~~~~~~~~~.***.....~~~~~~~~~~~.***...~~~~~~~~~~~~~~#",

                    "...tttttttttttt...~~...ttt....c.ttt..~~~...ttta...c.ttt..~~~...tttac.a..htt..~~~...ttt...a..ttt..~~~...ttt......ttt..~~~...ttt......ttt..~~~...tttttttttttt.~~~~...tttttttttttt.~~~~ttttttttttttt...~~~~ttty..yttttt....~~~~ttttt.ttt....~~~~~~~ttttt.ttt...~~~~~~~~ttttttttt..~~~~~~~~~ttttttttt..~~~~~~tttttttttttt.~~~~~~..ttttttytttt~~~~~~..f.ttttt.tttt~~~~~~....ttttt...tt~~~~~~tt.ttttttytttt~~~~~~ttttt#HERO,0,0,IS,1,0,YOU,2,0,REDWALL,0,2,IS,1,2,STICKY,2,2,GRAYWALL,0,4,IS,1,4,STICKY,2,4,LOVE,0,6,IS,1,6,GOAL,2,6,TREE,0,8,IS,1,8,SOLID,2,8,GRAYWALL,4,10,IS,5,11,YOU,5,12,REDWALL,4,18,IS,5,18,YOU,6,18,#",

					//31
					"ttttttttttttttttttttttttttttttt..ttttttttt.....t.........tttt................tttt...y............tttt...............ttttt...............ttttt........t......tttttttt.tttttt.....tttttttt.tttttt...tttttttttt.tttt.tttttttttt~ttttttt.......ttttt~~tttttt........tttt~~~~~~~~........tttt~~~~p~~~.......ttttt~~~.f.~~......tttttt~~~.h.~~.....ttttttt~~~~~~~~.....ttttttt~~~~~~~~~~~~~~~~~~~~~~...~~...~~...~~~~~#IF,11,2,THEN,2,4,ROCK,10,4,AND,8,5,IS,12,5,TREE,4,8,FIRE,12,8,IS,4,9,SOLID,4,10,ICE,11,11,PADLOCK,9,13,WALL,11,15,WOMAN,2,19,IS,3,19,GOAL,4,19,HERO,7,19,IS,8,19,YOU,9,19,WATER,12,19,IS,13,19,KILL,14,19,#",

                    "iiiiiiiiiiiiiiiiiiii" + 
                    "~~~~~~~~~~~~~~~~~~~~" +
                    "tttt.....ttt.......~" +
                    "ttt......iii.t.....~" +
                    "tt......fttttt.....~" +
                    "tt.......iii.......~" +
                    "ttitttttitttitt.iii~" +
                    "ttitttttitttitttiti~" +
                    "ttitttttitttitttiti~" +
                    "t.yiiiii.iiil.h.iti~" +
                    "t.itttttitttitttiti~" +
                    "tiitttttittti.ttiti~" +
                    "tiitttttittti.ttiii~" +
                    "t.fiiiiiriii~.tt...~" +
                    "t..ttttt.tttttt....~" +
                    "t..........ttt.....~" +
                    "t...........tt.....~" +
                    "t...........tt.....~" +
                    "~~~~~~~~~~~~~~~~~~~~" +
                    "iiiiiiiiiiiiiiiTTTii#" +
                    "HERO,0,0,IS,1,0,YOU,2,0,HERO,9,0,IS,10,0,HOT,11,0,LOVE,14,0,IS,15,0,GOAL,16,0,FIRE,4,4,IS,5,4,KEY,6,4,ROCK,15,4,IS,16,4,PUSH,17,4,HERO,13,11,IS,13,12,FIRE,15,15,IS,16,15,SOLID,17,15,ICE,0,19,IS,1,19,SLIP,2,19,TREE,5,19,IS,6,19,SOLID,7,19,PADLOCK,10,19,IS,11,19,LOCK,12,19,WATER,15,19,IS,16,19,SINK,17,19,#iiiiiiiiiiiiiiiiiiii~~~~~~~~~~~~~~~~~~~~...................~.........iii.......~...................~.........iii.......~..i.....i...i...iii~..i.....i...i...i.i~..i.....i...i...i.i~...iiiii.iii....i.i~..i.....i...i...i.i~.ii.....i...i...i.i~.ii.....i...i...iii~...iiiii.iii~......~...................~...................~...................~...................~~~~~~~~~~~~~~~~~~~~~iiiiiiiiiiiiiiiiiiii#",

					"tttttttt          ttttttttt  ...~~... tttttt.tt ....~~... tttttt.tt ....~~... tttttt.tt ....~~... ttttttt   ....~~... ttttttt ......~~... ttttttt .y....~~.p. ttttttt ......~~... ttttttt .r..r.~~... ttttttt ......      tttttt  ...... ttttttttttt ....... ttt...ttttt  .....  tttttttttttt       ttt...tttttttttttttttttttttttttttttttttttttttttt~~~~~~~~~~~~~~~~tttt~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#ROCK,4,2,WOMAN,15,2,IS,4,3,ROCK,10,3,IS,15,3,GHOST,4,4,IS,10,4,GOAL,15,4,PUSH,10,5,HERO,5,12,IS,6,12,YOU,7,12,WATER,16,12,IS,17,12,SINK,18,12,TREE,15,14,IS,16,14,SOLID,17,14,#",

					//"t...t...t...t...tttttttttttttttttttttt.ttttttttttttttttttt.t..............***t.t..............***..t~..yw....w..w.***.ft~~............***..t~~~~~~~~~~~~~~***.tt~~~~~~~~~~~~~~***ttt~~~~~~~~~~~~~~***ttttt............***tt.tt...w...w...****tt.tt...........***.tt.tt.w...w.....***tttttt..........t***.tttttt........tt****tttttt.......ttt*****tttttt.....tttt******tttttttttttttt*******tttttttttttttt******#WALL,1,0,IS,2,0,MELT,3,0,WATER,5,0,IS,6,0,SINK,7,0,HERO,9,0,ISNOT,10,0,SINK,11,0,TREE,13,0,IS,14,0,SOLID,15,0,FIRE,18,1,IS,18,2,GOAL,18,3,HERO,5,4,IS,6,4,YOU,7,4,STICKY,7,5,GRAYWALL,3,10,LAVA,19,10,IS,7,11,AND,11,11,IS,19,11,HOT,19,12,SNOWBALL,10,13,WALL,5,15,IS,6,15,PUSH,7,15,#",

					".aa........wwiiiiii..a.....p....wiiiiii..............iiiiii....y.........iiiiiii..........f..iiiiii..........i.i.iiiiii...........i..iiiiii....i.iii.....iiiiiii...ihiiii...iiiiiiii...iiiiiiiiiiiiiiiii...iiiiiiiiiiiiiiiii...iiiiiiiiiiiiiiiii.........~~~iiiiiiii......~~~~~~iiiiiiiittttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttuttttttttttttttttttttttttt#IF,0,0,ICE,17,0,TREE,19,0,HERO,0,1,IS,4,1,ISON,9,1,IS,17,1,IS,19,1,ISON,0,2,LOVE,2,2,AND,11,2,KILL,17,2,PUSH,19,2,LOVE,0,3,WOMAN,6,3,IS,7,3,YOU,8,3,THEN,0,4,STAR,19,4,WATER,0,5,ISNOT,3,5,HERO,10,5,IS,19,5,IS,0,6,GOAL,19,6,SINK,0,7,FIRE,19,8,IF,1,9,IS,19,9,LOCK,19,10,WALL,1,11,KEY,8,12,#.............iiiiii..............iiiiii..............iiiiii..............iiiiiii.............iiiiii..........i.i.iiiiii...........i..iiiiii....i.iii.....iiiiiii...i.iiii...iiiiiiii...iiiiiiiiiiiiiiiii...iiiiiiiiiiiiiiiii...iiiiiiiiiiiiiiiii.........~~~iiiiiiii......~~~~~~iiiiiiii.~~~~~~~~~~~........~~~~~~~~~~~~........~~~~~~~~~~..~~.......~~~~~~~~~~~~~~~~.....~~....~~~~~~.~~~...............~~~~...#",

                    "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~tt~~~tttt~t~~~~~~~~~ttttt...ut.t~~~~~~~~tttttr.rt..t~~~~~~~~tttttt.t.y.t~~~~~~~~ttttt..t.t.t~~~~~~~~~~ttt..t..rt~~~~~~~~~~~ttt..r.tt~~~~~~~~~~~ttttttttt~~~~~~~~~~~ttttttttt~~~~~~~~~~~ttttttt~~~~~~~~~~~~~~~tttt~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...~...~...~~~~~~~~~~~~~~~~~~~~~~~~~~~~~...~...~...~...~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~#GRAS,1,15,IS,2,15,SLIP,3,15,STAR,5,15,IS,6,15,GOAL,7,15,TREE,9,15,IS,10,15,SOLID,11,15,HERO,1,17,IS,2,17,YOU,3,17,ROCK,5,17,IS,6,17,YOU,7,17,HERO,9,17,IS,10,17,KEY,11,17,STAR,13,17,IS,14,17,LOCK,15,17,#",

					".t.t.t.*.*.*.*.tt.t..t.t.t.*.*.*.*.tt.t..t.t.t.*.*.*.*.tt.t..t.t.t.********ttttt.t.t.t.***ttttttttt..t.t.t.***tp~tttttt..t.t.t.***tt~tttttt..t.t.t.***tiyiiitttttttttt****tiiihittt.tttttt***.tiiiiittt.tttt*****.tiitiittt.ttt******.tiuiiitttttt******..tiiitittt.tt*****...tttttttt~.t*****..f.ttttttt~~.******....~~~~~~~~~~****.....~~~~~~~~~~.****....~~~~~~~~~~~.***.....~~~~~~~~~~~.***...~~~~~~~~~~~~~~#IF,0,0,IF,2,0,IF,4,0,IF,6,0,GRAS,8,0,WOMAN,10,0,ROCK,12,0,HERO,14,0,HERO,17,0,TREE,19,0,HERO,0,1,HERO,2,1,HERO,4,1,HERO,6,1,IS,8,1,IS,10,1,IS,12,1,ISNOT,14,1,IS,17,1,IS,19,1,ISON,0,2,ISON,2,2,ISON,4,2,ISON,6,2,SLIP,8,2,ALLGOAL,10,2,WINABLE,12,2,SLIP,14,2,YOU,17,2,SOLID,19,2,LOVE,0,3,LOVE,2,3,LOVE,4,3,LOVE,6,3,THEN,0,4,THEN,2,4,THEN,4,4,THEN,6,4,HERO,19,4,WATER,0,5,STAR,2,5,LOVE,4,5,ICE,6,5,IS,19,5,IS,0,6,IS,2,6,IS,4,6,IS,6,6,HOT,19,6,GRAS,0,7,ROCK,2,7,WOMAN,4,7,TREE,6,7,ICE,19,8,IS,19,9,MELT,19,10,WATER,19,12,IS,19,13,SOLID,19,14,ROCK,19,16,IS,19,17,PUSH,19,18,#.......*.*.*.*.............*.*.*.*.............*.*.*.*.............********............***.................***.~~..............***..~..............***.i~iii..........****.iii.i..........***..iiiii........*****..ii.ii.......******..iiiii......******...iii.i......*****...........~..*****...........~~.******....~~~~~~~~~~****.....~~~~~~~~~~.****....~~~~~~~~~~~.***.....~~~~~~~~~~~.***...~~~~~~~~~~~~~~#",

                    "...t...t...~...~~~~~tttttttt.~~~~~~~~~~~...t...t.~~~...~~~~~ttttttt~~~~~~~~~~~~~ttt...ttttttttt~~~~~t......t~tttttttt~~~t.f.....~~iittttt~~~t.......~~iiitttt~~~t...w...~~iiiittt~~~t...w.p.~~ihiittt~~~t...w...~~iiiitttt~~t...w...~~iiiittt~~~ttt....t~tiiitttt~~~tttt..tttttttttt~~~~tttt......tttt~~~~~~tt.........t~~~~~~~~tt.........t~~~~~~~~ttttt...tttt~~~~~~~~tttttttttttt~~~~~~~~tttttttttttt~~~~~~~~#WOMAN,0,0,IS,1,0,YOU,2,0,TREE,4,0,IS,5,0,SOLID,6,0,WALL,8,0,IS,9,0,PUSH,10,0,WATER,12,0,IS,13,0,SINK,14,0,IS,8,1,ICE,0,2,IS,1,2,KILL,2,2,WALL,4,2,IS,5,2,MELT,6,2,STICKY,8,2,LOVE,12,2,IS,13,2,GOAL,14,2,IS,5,5,HOT,2,9,EMPTY,7,14,GRAS,4,15,ICE,9,15,FIRE,6,16,#...........~...~~~~~.........~~~~~~~~~~~.........~~~...~~~~~.......~~~~~~~~~~~~~...............~~~~~........~........~~~........~~ii.....~~~........~~iii....~~~........~~iiii...~~~........~~iiii...~~~........~~iiii....~~........~~iiii...~~~........~.iii....~~~................~~~~..............~~~~~~............~~~~~~~~............~~~~~~~~............~~~~~~~~............~~~~~~~~............~~~~~~~~#",

					"...~~~~~~~~~~~~~~ttt~~~~~~....f.~~~~~ttt~~..f.........~~~ttt~.f..s..s......ttttt~...........f.tttttt~.............tttttt~..tt...t.....t...tt~y.t....t.s...t.h.tt~..tt...t.....t...tt~...t...t.....tttttt~r............tttttt~..........l...ttttt~~~.............tttt~~~~..r...f..bb.tttt~~~~~~.......b..tttt~~~~~~~.....wbw.tttt~~~~~~~~....wwwttttt~~~~~~~~~~...ttttttt~~~~~~~~~~~.tttttttt~~~~~~~~~~~ttt...ttt#WATER,0,0,IS,1,0,SINK,2,0,FIRE,5,6,IS,6,6,KILL,7,6,ROCK,5,7,IS,6,7,HOT,7,7,LOVE,5,8,IS,6,8,GOAL,7,8,HERO,5,9,IS,6,9,YOU,7,9,TREE,14,19,IS,15,19,SOLID,16,19,#",

					".......~.t...t...ttt..uuu..~.......ttttt.......~.......ttttt.......~....h..tttttt.y....~.......tttttt......~..p....tttttt......~.h.....tttttt......~.......tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt.tttttttttttttttttt.~ttttttttttttttttt~~~tttttttttttttt.~~~~ttttttttttt..~~~~~~ttttttt.s....~~~~~ttttttt.......~~ttttttt.tt....~~~~~ttttf.l..t...~~~~~tttttt..ttt...~~~~ttttttttttttt#HERO,0,0,IS,1,0,YOU,2,0,LOVE,10,0,IS,11,0,PUSH,12,0,LOVE,14,0,IS,15,0,MELT,16,0,WOMAN,1,1,STAR,14,1,IS,0,2,IS,14,2,GOAL,0,3,WATER,4,3,KILL,14,3,IS,4,4,SINK,4,5,TREE,1,7,IS,2,7,SOLID,3,7,WATER,12,7,IS,13,7,HOT,14,7,#",

                    "...t...t...t...ttttt...t...t...t...ttttttttttttttttttttttttttt...............ttttt...h...s........ttt..s..............ttt.......hs....s...tttts................ttt....s....s.......t~~~~~~~~~~~~~~~~~~~~ttt................tttt................ttt.............h...tt.....p............tt..................tt...........h......tt..................tt.................tttt...............ttttttttttttttttttttttt#HERO,0,0,IS,1,0,YOU,2,0,LOVE,4,0,IS,5,0,ALLGOAL,6,0,TREE,8,0,IS,9,0,SOLID,10,0,HERO,12,0,IS,13,0,MELT,14,0,WOMAN,0,1,IS,1,1,YOU,2,1,WATER,4,1,IS,5,1,HOT,6,1,WOMAN,12,1,IS,13,1,MELT,14,1,SNOWBALL,8,11,IS,4,12,SOLID,9,13,PUSH,2,14,LOVE,4,15,EMPTY,10,15,HERO,7,16,#",

					"~...~...~...~...~...~~~~~~~~~~~~~~~~~~~~~~~~~~hh~hh~~~~~~~~~~~~~~h..h..h~~~~~~~~.~~~~h.....h~~~~~~~~.~~~~h.....h~~~~~~~~.~~~~~h.y.h~~~~~~~~~~~~~~~~h.h~~~~~~~~~~~~~~~~~~h~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~t~~~tt~~~~~~~~~~~~~tt....ttt~~~~~~~~~~~tt..p..ttt~~~~~~~~~~ttt.....tt~~~~~~~~~~tttt...tt~~~~~~~~~~~tttttttt~~~~~~~~~~~~tttttt~~~~~~~~~#LOVE,1,0,IS,2,0,KILL,3,0,TREE,5,0,IS,6,0,SOLID,7,0,WATER,9,0,IS,10,0,SINK,11,0,FIRE,13,0,IS,14,0,PUSH,15,0,WOMAN,17,0,IS,18,0,GOAL,19,0,HERO,0,4,FIRE,7,4,IS,8,4,LOVE,9,4,IS,0,5,YOU,0,6,#",

                    "...tttttttttttttttttttttttt........tttt*...ttt.....u....ttt*ttttt........r...t**tttttt............**tttttt....p.f.....********............**tt*****..........***tttt***.........****ttttt******....*****tttt..*************tttt...............tttt..y.............t.tt...............tt.tt....h.......f..tt.tt.........r.....tttttt......u.......tt.tttt......t.t....tt.ttttt....ttttt..ttt.tttttttttttttttttttt#TREE,0,0,IS,1,0,SOLID,2,0,STAR,0,2,IS,1,2,ALLGOAL,2,2,LAVA,7,3,IS,8,3,HOT,9,3,PUSH,16,3,WOMAN,13,7,HERO,11,11,IS,12,11,YOU,13,11,MELT,6,12,LAVA,19,12,IS,19,13,IS,4,14,IS,8,14,KILL,19,14,KEY,6,16,LAVA,19,16,ROCK,11,17,IS,19,17,SLIP,19,18,#",
                    
                    "....................~~~~~~~~~~~~~~~~~~~~~~~~~ttttttttt~~~~~~~~~~ttttttttttt~~~~~~~tttttt....ttttt~~~~tttttt.....ttttttt~~tttttt.a.~.ttttttttttttttt.a.~......tttt....~~.attb.......tt.p.t~~~aw.baaaw.y.tt....~~.cttb...w...tttttttt.c.~......tttttttttt.c...ttttttttttttttt.c...tttttttttttttttt....tttttttttttttttttttttttttttt...t...t...t...ttttt.ttt.ttt.ttt.ttttttt.ttt.ttt.ttt.ttttttt....................#HERO,0,0,IS,1,0,YOU,2,0,WOMAN,4,0,IS,5,0,GOAL,6,0,TREE,8,0,IS,9,0,SOLID,10,0,WATER,12,0,IS,13,0,SINK,14,0,BLUEWALL,0,16,IS,1,16,STICKY,2,16,REDWALL,4,16,IS,5,16,STICKY,6,16,GRAYWALL,8,16,IS,9,16,STICKY,10,16,WALL,12,16,IS,13,16,STICKY,14,16,IS,0,17,IS,4,17,IS,8,17,IS,12,17,PUSH,0,18,PUSH,4,18,PUSH,8,18,PUSH,12,18,#",

					".t.t.t.*.*.*.*.tt.t..t.t.t.*.*.*.*.tt.t..t.t.t.*.*.*.*.tt.t..t.t.t.********ttttt.t.t.t.***ttttttttt..t.t.t.***ttt~p~ttt..t.t.t.***ttt~t~ttt..t.t.t.***ttiiyiitttttttttt***ttiihiitt.ttttttt**.tiiiiiiit.tttt*****.tiititiit.ttt******.tiiuiuiitttt******..tiiiiiiit.tt*****...tttttttt~.t*****..f.ttttttt~~.******....~~~~~~~~~~****.....~~~~~~~~~~.****....~~~~~~~~~~~.***.....~~~~~~~~~~~.***...~~~~~~~~~~~~~~#IF,0,0,IF,2,0,IF,4,0,IF,6,0,GRAS,8,0,WOMAN,10,0,ROCK,12,0,HERO,14,0,HERO,17,0,TREE,19,0,HERO,0,1,HERO,2,1,HERO,4,1,HERO,6,1,IS,8,1,IS,10,1,IS,12,1,ISNOT,14,1,IS,17,1,IS,19,1,ISON,0,2,ISON,2,2,ISON,4,2,ISON,6,2,SLIP,8,2,ALLGOAL,10,2,WINABLE,12,2,SLIP,14,2,YOU,17,2,SOLID,19,2,LOVE,0,3,LOVE,2,3,LOVE,4,3,LOVE,6,3,THEN,0,4,THEN,2,4,THEN,4,4,THEN,6,4,HERO,19,4,WATER,0,5,STAR,2,5,LOVE,4,5,ICE,6,5,IS,19,5,IS,0,6,IS,2,6,IS,4,6,IS,6,6,HOT,19,6,GRAS,0,7,ROCK,2,7,WOMAN,4,7,TREE,6,7,ICE,19,8,IS,19,9,MELT,19,10,WATER,19,12,IS,19,13,SOLID,19,14,ROCK,19,16,IS,19,17,PUSH,19,18,#.......*.*.*.*.............*.*.*.*.............*.*.*.*.............********............***.................***...~~~...........***...~.~...........***.iii~iii.........***.iii.iii.........**..iiiiiii......*****..iiiiiii.....******..ii.i.ii....******...iiiiiii....*****...........~..*****...........~~.******....~~~~~~~~~~****.....~~~~~~~~~~.****....~~~~~~~~~~~.***.....~~~~~~~~~~~.***...~~~~~~~~~~~~~~#",

                    "...t...t...ttttttttttttttttttttttttttttt...tttttt.........tt.tttttttt.h.......tt.t................tttt..............tttt.tt...ty........tttt.tt...tttttltitttttt.tt.ttt..........tttttt............b..tt.tt.b.............tt.tt.....b.........tt.ttttttbbt..tttttttttttttttttttttttttttttt.ttttttttttttttttttt.tttttttt~~~~~~~~~...ttttttt~~~~.~~~~~ttttttttt~~~~~.~~~~~ttttttt~~~~~~~~~~~~~tttttt~~~~~~~~~~~~~~#TREE,0,0,IS,1,0,SOLID,2,0,PADLOCK,4,0,IS,5,0,LOCK,6,0,WATER,8,0,IS,9,0,KILL,10,0,BLUEWALL,0,2,IS,1,2,PUSH,2,2,IS,0,3,LOVE,15,3,IS,16,3,STICKY,0,4,PUSH,5,5,IS,10,5,KEY,12,5,ICE,0,6,HERO,3,6,IS,4,6,YOU,5,6,IS,0,7,MELT,0,8,HOT,5,9,ICE,0,10,KILL,12,10,IS,0,11,GOAL,6,11,SLIP,0,12,LOVE,2,14,IS,2,15,HERO,0,16,IS,1,16,HOT,2,16,#",

					"...~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~tttt~~~........ttttttttt~~...y.....r.....ttt~~.........t..t..ttt~~.........t..t..ttt~~.....ttttt..tttttt~~.....t...t.....ttt~~....hh.p.t.....ttt~~....tt...t.....ttt......ttttttttttttttttttttttttttttttttttttttttttttttttttttt~tttttttttttttttttt~~~~~tttttttttt~~~~~~~~~tttttttt~~~~~~~~~~~~tttttt~~~~~~~~~~~~~~ttttt~~~~~~~~~~~~~#WATER,0,0,IS,1,0,KILL,2,0,LOVE,14,5,WOMAN,15,5,HERO,4,6,IS,5,6,YOU,6,6,IS,15,6,IS,16,6,KILL,15,7,GOAL,16,7,ROCK,13,10,IS,14,10,PUSH,15,10,TREE,0,12,IS,1,12,SOLID,2,12,#",

                    "...t...t...t...ttttt~~~~tttttttttttttttt~~~ttttttttttttttttt~~tttt~.~.~.~.~....ttttttt.~.~.~.~.t.p.ttttttt~.~.~.~.~tt..ttttttt.~.r.tttttttttt.....~.~.~tttttttttt.rrr..~.~.ttttttttttyr.r.~.~.~tttttttttt.rrr..~.~.ttttttt~~t......~~.~ttttt~~~~t.....tttttttttt~~~~t..........tttt~~~~tt...........ttt~~~tttt..........ttt~~~ttttt.........tt~~~~tttttt......tttt~~~ttttttttttttttttt~~tttttttttttttttttt~~tttt#HERO,0,0,IS,1,0,YOU,2,0,WOMAN,4,0,IS,5,0,GOAL,6,0,TREE,8,0,IS,9,0,SOLID,10,0,WATER,12,0,IS,13,0,KILL,14,0,ROCK,3,9,PUSH,5,11,SINK,9,11,IS,2,13,IS,7,14,WATER,4,15,IS,10,15,HOT,7,17,#",
                    
                    "...ttttttttttttt...~ttttttttttt...tt~~~~...tttttt...u.tt~~~~ttttttt.......ttt~~~...ttt..p......tt~~~tttttt.........tt~~~ttttt..s.....u.ttt~~.tttiiiiiiiiiiittt~~.tt.............tt~~.tt......p......tt~~ttt.............tt~~ttt..u....iii...ttt~.ttiiiiiiiihiiiittt~.ttt....ii.......tt~.tttt...........ttt~ttttt..p........ttt~.ttttt........u.tt~~.tttttt.........tt~~.ttttttttttttttttt~~ttttttttttttttttt~~~#WOMAN,0,0,IS,1,0,YOU,2,0,WATER,16,0,IS,17,0,SINK,18,0,STAR,0,2,IS,1,2,ALLGOAL,2,2,SOLID,12,3,TREE,0,4,IS,1,4,SOLID,2,4,ICE,11,4,SNOWBALL,9,5,LOCK,6,6,ICE,0,7,IS,0,8,KILL,0,9,WATER,5,9,IS,10,9,PUSH,13,9,KEY,7,11,ICE,0,12,IS,0,13,SLIP,0,14,HOT,12,14,GRAS,10,15,STAR,0,16,IS,8,16,IS,0,17,MELT,0,18,#",
        };
	
}
