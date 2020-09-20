package com.basic;

import com.frame.entity.Account;
import com.google.common.base.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xsy
 * @create 2017-02-21 15:09
 * @desc string
 **/
@Slf4j
public class StringTest {

    public static void main(String[] args) {
        //subString：jdk6和jdk7的区别
        String str1 = "abcdef";
        str1 = str1.substring(3, 3);
        System.out.println(str1);

//        urlIsWhite("service/user/1");
        urlIsWhite("service/goods/infos");
//        String testUrl = "https://qxymgr-d.ywsoftware.cn/files\\upload\\201901\\ba4e7775-52c4-4aba-864f-804c306b2279.png";
        String testUrl = "https://qxymgr-d.ywsoftware.cn/files/upload/201901/ba4e7775-52c4-4aba-864f-804c306b2279.png";
        System.out.println("replace testUrl=" + testUrl.replace("\\", "/"));
        int i = 1;
        String.valueOf(i);//内部通过调用Integer.toString(i)
        str1 = "abcdef";
        char char_1 = str1.charAt(0);
        String str_char = String.valueOf(char_1);
        System.out.println("str_char=" + str_char);

        // 通过对实参的hashCode()方法返回的int hash值与各个case中hashCode()方法返回的int hash值比较，所以实参和case不能为null
//        stringSwith(null);

        String s1 = "a";
        System.out.println((s1 + "b") == "ab");//运行时确定s1，s1 + "b"是堆中新对象
        System.out.println((s1 + "b").equals("ab"));//true
        String s4 = s1 + "d";
        String s5 = s1 + "d";
        System.out.println(s4 == s5);////运行时确定s4、s5，是堆中新对象
        //intern():如果字符串常量池中没有这个字符则添加，然后返回这个引用；如果有则直接返回这个引用
        System.out.println("intern() 比较======");
        System.out.println((s1 + "c").intern() == "ac");
        System.out.println(s4.intern() == s5.intern());

        String s2 = "c" + "d";//编译期常量折叠成String s2 = "cd"
        System.out.println(s2 == "cd");

    }

    @Test
    public void testReplace() {
        //replace：
        //  字符串中\需要用来“\\”表示，例如System.out.println( "\\" ) ;只打印出一个"\"。" +
        //  正则表达式中也用\表示转义。\在正则表示式中也作为转义字符使用，所以在正则表达式中"\\\\"才表示一个反斜杠，即"\"
        //  对于replaceFirst和replaceAll中的replacement，如果其中包含\的话，将作为转义字符看待
        // replace和replaceAll都是全部替换，replaceAll,replaceFirst用正则
        System.out.println("\\");
        System.out.println("aaa".replace("a", "\\."));// output：\.\.\.
        System.out.println("aaa".replace("a", "\\\\."));// output：\\.\\.\\.
        System.out.println("aaa".replaceAll("a", "\\."));// output：...
        System.out.println("aaa".replaceAll("a", "\\\\."));// output：\.\.\.
        System.out.println("aaa".replaceFirst("a", "\\."));// output：.aa
        System.out.println("aaa".replaceFirst("a", "\\\\."));// output：\.aa
        System.out.println("aaa.bb.cc".replaceAll(".", "/"));//"."在正则中表示所有字符。output：/////////

        String htmlContent = "<p style=\"text-indent: 2em\">12月15日，2019首届中国移动电子竞技大赛湖南总决赛在长沙市开福万达广场举行，成功杀进湖南总决赛的选手都拥有不俗实力，对于他们来说距离全国舞台仅有一步之遥，因此每场比赛大家都是全力以赴更为在场观众送上了无数个闪耀的精彩瞬间！</p> \n" +
                "<p style=\"text-indent: 2em\"><strong>【iG表演赛：妖姬致命平A单杀职业选手】</strong></p> \n" +
                "<p style=\"text-indent: 2em\">除了正赛三大项目的决赛， iG战队职业选手Forge和Yi分别率领观众进行对抗的水友赛以及Forge VS 英雄联盟SOLO项目冠军的表演赛也是本次湖南总决赛的一大亮点。在面对职业大神Forge时，CMCC选手任波顶住压力，秀出了自己的风格。</p> \n" +
                "<p style=\"text-indent: 2em\">第一场Forge刀妹对战任波辛德拉，刀妹在精准计算伤害后越塔强杀辛德拉，让我们看到了职业选手扎实的基本功。而在第二场中，Forge选择青钢影遇上任波操刀的妖姬，双方激情在一阵互拼后，任波利用致命平A单杀了职业选手，全场观众也报以欢呼！虽然最终以1:2不敌Forge，但任波的SOLO实力有目共睹，冠军荣誉更是实至名归。</p> \n" +
                "<p style=\"text-align: center\"><a href=\"/showpic.htm?img=http://pic2..com/files/191217/5613886_181018_1.jpg\" target=\"_blank\" style=\"display:inline-block;\"><img src=\"http://pic2..com/files/191217/5613886_181018_1.jpg\" width=\"600\" border=\"0\" height=\"377\" alt=\"任波黑血单杀职业选手\" title=\"任波黑血单杀职业选手\"></a></p> \n" +
                "<p style=\"text-indent: 2em; text-align: center\">任波黑血单杀职业选手</p> \n" +
                "<p style=\"text-indent: 2em\"><strong>【炉石传说：劣势稳扎稳打，优势一鼓作气】</strong></p> \n" +
                "<p style=\"text-indent: 2em\">《炉石传说》决赛在徐鑫涛和李继烽两位选手之间展开，势均力敌的两人经过20多分钟苦战才分出胜负。在第一场潜行者对战萨满的战斗中，理论上李继烽的萨满占据了一定优势，但徐鑫涛的亡语贼却取得了场面上的主动，依靠无尽的亡语资源不断消耗对手，李继烽两张闪电风暴都没法完成清场，最终落败。而在KOF2赛制的第二场，李继烽的元气骑在面对潜行者时处于劣势，反观徐鑫涛就抓住了职业优势，一举击败对手，成功夺冠！</p> \n" +
                "<p style=\"text-align: center\"><a href=\"/showpic.htm?img=http://pic2..com/files/191217/5613886_181031_1.jpg\" target=\"_blank\" style=\"display:inline-block;\"><img src=\"http://pic2..com/files/191217/5613886_181031_1.jpg\" width=\"600\" border=\"0\" height=\"466\" alt=\"徐鑫涛赢得炉石冠军\" title=\"徐鑫涛赢得炉石冠军\"></a></p> \n" +
                "<p style=\"text-align: center\"><a href=\"/showpic.htm?img=http://pic2..com/files/191217/5613886_181032_2.jpg\" target=\"_blank\" style=\"display:inline-block;\"><img src=\"http://pic2..com/files/191217/5613886_181032_2.jpg\" width=\"600\" border=\"0\" height=\"401\" alt=\"徐鑫涛赢得炉石冠军\" title=\"徐鑫涛赢得炉石冠军\"></a></p> \n" +
                "<p style=\"text-indent: 2em; text-align: center\">徐鑫涛赢得炉石冠军</p> \n" +
                "<p style=\"text-indent: 2em\"><strong>【英雄联盟Solo赛：同人物对决更显选手硬实力】</strong></p> \n" +
                "<p style=\"text-indent: 2em\">在《英雄联盟》Solo决赛中，任波选手遭遇强敌杨馥宁，第一局，双方选出小炮和卢锡安，任波的卢锡安依靠草丛W丝血险胜。也许两位选手都看到了卢锡安的强势，又可能大家想以同一个角色决出胜负，所以在第二局和第三局中，两人全都选择了卢锡安。第二场，杨馥宁强攻点燃后安越塔强杀扳回一城。最终决战，任波卡大招CD先下手为强，杨馥宁打出GG，任波2:1获胜并赢得了SOLO赛冠军。</p> \n" +
                "<p style=\"text-align: center\"><a href=\"/showpic.htm?img=http://pic2..com/files/191217/5613886_181045_1.jpg\" target=\"_blank\" style=\"display:inline-block;\"><img src=\"http://pic2..com/files/191217/5613886_181045_1.jpg\" width=\"600\" border=\"0\" height=\"293\" alt=\"卢锡安VS卢锡安，湖南最强SOLO实至名归\" title=\"卢锡安VS卢锡安，湖南最强SOLO实至名归\"></a></p> \n" +
                "<p style=\"text-indent: 2em; text-align: center\">卢锡安VS卢锡安，湖南最强SOLO实至名归</p> \n" +
                "<p style=\"text-align: center\"><a href=\"/showpic.htm?img=http://pic2..com/files/191217/5613886_181053_1.jpg\" target=\"_blank\" style=\"display:inline-block;\"><img src=\"http://pic2..com/files/191217/5613886_181053_1.jpg\" width=\"600\" border=\"0\" height=\"401\" alt=\"任波赢得LOL Solo项目冠军\" title=\"任波赢得LOL Solo项目冠军\"></a></p> \n" +
                "<p style=\"text-indent: 2em; text-align: center\">任波赢得LOL Solo项目冠军</p> \n" +
                "<p style=\"text-indent: 2em\"><strong>【英雄联盟团队赛：多次完美团战奠定胜局】</strong></p> \n" +
                "<p style=\"text-indent: 2em\">《英雄联盟》团队决赛在岳阳正月初五影业战队和郴州SEA战队之间展开。正月初五影业队直落两局，以2:0完胜对手，虽然场上10位选手在个人能力上相差无几，但在团队配合和团战表现上，正月初五影业队明显占据上风。两局中，他们多次打出堪称完美的团战，如第一局22分钟，双方5v5开团， 正月初五选手凭借塔下强杀团灭对手；第二局14分钟，正月初五峡谷推中，霞依靠完美的团战发挥斩获三杀，10分钟后中路再次爆发团战，正月初五双C在拉扯中创造出极佳输出环境，最终赢得比赛。</p> \n" +
                "<p style=\"text-align: center\"><a href=\"/showpic.htm?img=http://pic2..com/files/191217/5613886_181103_1.jpg\" target=\"_blank\" style=\"display:inline-block;\"><img src=\"http://pic2..com/files/191217/5613886_181103_1.jpg\" width=\"600\" border=\"0\" height=\"337\" alt=\"正月初五影业队双C完美输出\" title=\"正月初五影业队双C完美输出\"></a></p> \n" +
                "<p style=\"text-indent: 2em; text-align: center\">正月初五影业队双C完美输出</p> \n" +
                "<p style=\"text-align: center\"><a href=\"/showpic.htm?img=http://pic2..com/files/191217/5613886_181113_1.jpg\" target=\"_blank\" style=\"display:inline-block;\"><img src=\"http://pic2..com/files/191217/5613886_181113_1.jpg\" width=\"600\" border=\"0\" height=\"401\" alt=\"岳阳正月初五影业战队获得英雄联盟团队赛冠军\" title=\"岳阳正月初五影业战队获得英雄联盟团队赛冠军\"></a></p> \n" +
                "<p style=\"text-indent: 2em; text-align: center\">岳阳正月初五影业战队获得英雄联盟团队赛冠军</p> \n" +
                "<p style=\"text-indent: 2em\">中国移动电子竞技大赛湖南总决赛由移动双网双千兆全程提供网络支持，高速度低延迟的网络成为选手们出色发挥的最强助力，焕新升级的中国移动“动感地带”品牌在总决赛现场被青春点亮，更点亮了湖南电竞主场！湖南作为电竞之乡，选手们展现出的实力让我们叹为观止，希望他们能在全国总决赛中再造辉煌，为观众带来更多值得回味的华丽操作。随着5G生活向我们走来，焕新升级的“动感地带”必将带给用户更潮更有趣的流量+内容服务，书写不一样的5G时代青春故事。</p>";

//        htmlContent = htmlContent.replaceAll("<a href[^>]*>", "");
//        htmlContent = htmlContent.replaceAll("</a>", "");
//        htmlContent = htmlContent.replaceAll("<img[^>]*>", " ");
//        System.out.println(htmlContent);
//
//        //把所有<a标签内容和<img标签内容 替换为空
//        String contentString = "sdfsd abc---abc <a href='http://www.hao123.com'>" +
//                "http://www.hao123.com</a><img title='img' src='abc' />" +
//                "sdfsdfds";
//        contentString=contentString.replaceAll("<a href[^>]*>", "");
//        contentString=contentString.replaceAll("</a>", "");
//        contentString=contentString.replaceAll("<img[^>]*/>", " ");
//        System.out.println(contentString);

        //匹配空值
        System.out.println("is empty===" + ("".matches("\\s{0}")));
        //匹配http或者空值
        String httpOrEmptyStr = "(((http|https):\\/\\/)?((\\w)*|([0-9]*)|([-|_])*)+([\\\\.|/]((\\w)*|([0-9]*)|([-|_])*))+)|(\\s{0})";
        System.out.println("is http Or empty===" + ("".matches(httpOrEmptyStr)));
        System.out.println("is http Or empty===" + ("https://www.baidu.com".matches(httpOrEmptyStr)));
        //把空格去掉
        System.out.println("    str 1".replaceAll("\\s+", ""));
    }

    //uri是否是白名单中
    static boolean urlIsWhite(String uri) {
        String whiteUrlStr = "service/user/{id},service/user/login,service/goods/infos";
        List<String> whileApis = Arrays.asList(whiteUrlStr.split(","));
        if (whileApis.contains(uri)) {
            return true;
        }
        // path uri 处理
        for (String wapi : whileApis) {
            if (wapi.contains("{") && wapi.contains("}")) {
                if (wapi.split("/").length == uri.split("/").length) {
                    String reg = wapi.replaceAll("\\{.*}", ".*");
                    System.out.println(reg);
                    Pattern r = Pattern.compile(reg);
                    Matcher m = r.matcher(uri);
                    if (m.find()) {
                        System.out.println(uri + "@PathVariable is matcher");
                        return true;
                    } else {
                        System.out.println(uri + "@PathVariable is not matcher");
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @Test
    public void matcherTest() {
        String s1 = "service/user/{123}";
        String s2 = s1.replaceAll(".*\\{1,}", "666");
        System.out.println(s2);
    }

    public static String stringSwith(String id) {
        switch (id) {
            case "1":
                return "11";
            case "2":
                return "22";
        }
        return null;
    }

    @Test
    public void testEqualsOrDengyu() {
        Object o = "123";
        String s = "123";
        Object o2 = s;
        String s2 = (String) o;
        System.out.println(s.equals(o));

        String s3 = new String("123");
        System.out.println(s3 == s);
        System.out.println(s3.intern() == s);

    }

    @Test
    public void test2() {
        System.out.println(StringUtils.isNumeric("1.9"));//false
        System.out.println(isNumber("1.9"));
    }

    public boolean isNumber(String input) {
        if (input == null || "".equals(input)) {
            return false;
        }
        return Pattern.matches("-?[0-9]*(\\.?)[0-9]*", input);
    }

    @Test
    public void test3() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse("2019-04-08");
        System.out.println(date);
    }

    @Test
    public void test4() {
        int[] array = new int[2];//int基本类型数组被初始化为0
        System.out.println(array[0]);
    }

    @Test
    public void test5() {
        String s1 = "血常规：2019-06-27-1";
        String s2 = "血常规：2019-06-27-2";
        String s3 = s1.replace(s1, s2);
        System.out.println(s3);
    }

    //null + ""
    @Test
    public void test6() {
        String nullStr = null;
        String newStr = nullStr + "";
        System.out.println(newStr.length());
        if (StringUtils.isNotEmpty(newStr)) {
            System.out.println(newStr);
        }
    }

    //java.lang.String cannot be cast to java.lang.Integer
    @Test
    public void test7() {
        Object o = "6";
//        int resectionNum = (Integer) o;
        String str = (String) o;
        Object o2 = str;
//        Integer i = (Integer)str; //不能转换
        System.out.println(o);
    }

    @Test
    public void test8() {
        String s = "a";
        char c = 'a';
        int i = 97;
        char[] ch = {'a'};
        System.out.println(s.equals(c));
        System.out.println(s.equals(i));
        System.out.println(s.equals(ch));
    }

    public void fineTest() {
        //final修饰的普通变量，值不能改变
        final int i = 0;
//        i++;
        //final修饰的实例变量，引用不能改变，但引用的值可以改变
        final String str = "s";
//        str = "t";
        final Account account = new Account();
        account.setName("name");
//        account = new Account();
    }

    @Test
    public void test9() {
        log.info("test...");
    }

    @Test
    public void test10() {
        String str;
//        System.out.println(str);
        int i = 1;
        if (i == 1) {
            str = "st";
        }
//        System.out.println(str);
        if (i == 1) {
            str = "st";
        } else {
            str = "str";
        }
        System.out.println(str);
    }

    @Test
    public void test11() {
        String toString = Objects.toStringHelper(this).add("age", 18).add("name", "jack").toString();
        System.out.println(toString);
    }

    @Test
    public void testRegix() {
        //匹配1-20正整数
        boolean matches1 = "21".matches("(^[1][0-9]$)|(^[2][0]$)|(^[1-9]$)");
        System.out.println(matches1);
        //匹配1-50正整数
        boolean matches2 = "49".matches("(^[1-4][0-9]$)|(^[5][0]$)|(^[1-9]$)");
        System.out.println(matches2);
        //匹配51-100
        boolean matches3 = "100".matches("(^[5-9][0-9]$)|(^[1][0][0]$)");
        System.out.println(matches3);
        //匹配21-40正整数
        boolean matches4 = "21".matches("(^[2-3][0-9]$)|(^[4][0]$)");
        System.out.println(matches4);

        String regex = "http://dianjing\\.52pk\\.com/xinwen/xinwen_22458_(([1][0-9])|([2][0])|([1-9])).shtml";
        System.out.println("http://dianjing.52pk.com/xinwen/xinwen_22458_19.shtml".matches(regex));
    }
}
