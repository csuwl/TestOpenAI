package org.wl.testopenai;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.StreamingChatModel;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageGeneration;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.ai.openai.api.OpenAiImageApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@SpringBootTest
class TestOpenaiApplicationTests {
    @Value("${spring.ai.openai.base-url}")
    private String baseUrl = null;

    @Value("${spring.ai.openai.api-key}")
    private String apikey = null;

    @Test
    void contextLoads() throws IOException {
        OpenAiChatModel openAiChatModel = new OpenAiChatModel(new OpenAiApi(baseUrl, apikey),
                OpenAiChatOptions.builder().withModel("qwen-plus").build());
//        Flux<String> stream = openAiChatModel.stream(new UserMessage("帮我将小说做成漫画镜头文本，包括当前画面描述，字幕。每章大概5个画面"),
//                new UserMessage("青阳市水利局科员李睿年仅二十六岁就当上了副科级干部，在当地算是个年少得志的官场新进。可最近两年来他的仕途之路并不顺利。原来，一直提携他的老上司退休了，而新来的女上司又对他各种打压，眼看着升职无望，很多后来的同事都超了上来，心里很着急。\n" +
//                        "　　现在，他坐在酒桌旁，喝着五十六度的老白干，醉意渐浓，酒入愁肠愁更愁，想到自己的可悲处境，心里暗暗咒骂，奶奶的，她凭什么骑在老子头上作威作福，老子却打也打不得、骂也骂不得？老子跟她到底有什么深仇大恨，让她三百六十天如一日的将老子当奴隶一样使唤喝骂？是杀了她老爸了，还是抢了她老公了？这么想着，他下意识瞥了一眼坐在主位上的顶头女上司、水利局防汛办主任袁晶晶，心中恶狠狠的想着：“兔子急了还咬人呢，真把老子逼急了，跟你同归于尽！”\n" +
//                        "　　袁晶晶似乎感受到他的恶毒目光，从与别人的笑语声中抽出空来，回敬了他一个高傲而又凌厉的眼神。\n" +
//                        "　　这个眼神吓得李睿噤若寒蝉，酒醒了大半，忙垂下眼皮假作喝酒，心说这贱人喝了那么多酒居然还能保持霸道本色，看来自己注定被她吃得死死的。想到这，暗里长叹一声，唉，自己得罪谁不好，怎么偏偏得罪了这个女魔头呢？\n" +
//                        "　　提起袁晶晶，那可是青阳市水利局公认的局花，年轻貌美，体态婀娜，会穿衣会打扮，上下班都会开着一辆红色甲壳虫招摇过市。这样一个妖娆妩媚、富贵逼人的极品美女，几乎成了市局所有男人的梦中女神。可以这么说，是个男人，只要见过她一面，就想把她弄到手。李睿也曾对她怀有不切实际的邪恶想法，还曾觉得，她是自己的顶头上司，自己凭着英朗的外表可以近水楼台先得月。哪知道阴差阳错，办公室恋情没搞出来，反而变成了她的死敌。\n" +
//                        "　　李睿记得自己跟她结怨的经过，一共两次。\n" +
//                        "　　第一次是袁晶晶调到水利局任防汛办主任成为他顶头上司后的某天，他跟局里两个关系不错的同事在楼梯间里抽烟，不知怎么的就说起了她。男人凑到一起说起某个女人，尤其是美女，话题自然很不正经。其中一个说，她年纪轻轻能当上防汛办主任，完全因为她是现任局长张建设的情人，没看她整天往局长办公室跑？另外一个说，你那是扯淡，真正的内幕是，她是市委政法委书记、公安局局长冯卫东的情人，我亲眼见过冯卫东送她来上班。\n" +
//                        "　　当时因为袁晶晶的突然空降，阻碍了李睿升为实职副科，他心中有些不爽，就跟着发了一句牢骚，说，她长得就是小三儿的样儿。话音刚落，就见袁晶晶沉着一张俏脸从上层楼梯转了下来。她没看另外两人，冷飕飕的目光在李睿脸上打了个转就走了。从那天以后，李睿就成了防汛办的业务骨干，苦活累活脏活重活全由他一个人包了圆。李睿当然知道袁晶晶是在报复自己，可没办法，谁叫自己说错了话呢，只能认了。\n" +
//                        "　　第二次他犯的错则更过分了。水利局去年年终前在市里唯一的五星级酒店“盛景大酒店”举办年会，包了个大宴会厅。李睿不会跳舞也不爱唱歌，吃了些自助餐之后就坐在沙发上喝饮料。这时袁晶晶忽然坐到了他对面吧台的高脚椅上侧坐品酒。她是那次年会的女主持人，穿得特别迷人，上身是深V型的白色雪纺衫，下边是条黑色一步短裙，修长的大腿就那么露在外面，不着丝袜。当时她的坐姿不太雅观，两条腿在高脚椅上分开了差不多有四十五度。李睿有次抬头，无意间正好看到这幕不雅，说来怎么那么巧，他刚看了一眼，还没来得及转头，袁晶晶就发现了他的视线，她低头看了看，很自然就误会了他，虽然没当场发作，但自那天以后，李睿就彻底变成了她的眼中钉肉中刺。袁晶晶利用权力给他各种小鞋穿，轻则怒骂申斥，重则令他写检讨书，各种晋升的推荐选拔也将他排除在外。别说升迁无望，在办公室的地位也是一落千丈。\n" +
//                        "　　想起往事，李睿唏嘘不已，如果当初自己没说那句不该说的话、没看那个看了也白看的地方，就算现在跟袁晶晶产生不了办公室恋情，起码做个堂堂正正、有尊严的副主任科员还是可以的吧？这倒好，晋升无望，天天被她当驴一样的肆意斥骂使唤，什么时候是个头啊？唉，好吧，就当老子上辈子买了她当丫鬟没给钱，这辈子还债给她好了。\n" +
//                        "　　酒席终于结束，李睿起身就想回房睡觉，袁晶晶却叫住了他。\n" +
//                        "　　“李睿，你把这些防汛信息报告拿到我房里去。”袁晶晶一向是个能喝敢喝的女人，此时已经有几分醉意，平日里颐指气使的口吻此时显得轻飘飘的。\n" +
//                        "　　李睿早就留意到那些资料，一共十来页的4纸，捏在手里还不如一个打火机重，她袁晶晶回房休息的时候完全可以顺手拿回去。可就算这种小事她也不会放过，而是顺手拿来当做惩罚自己的一个机会。\n" +
//                        "　　李睿不情不愿的拿过那份报告，迈步就走，出了包间没走几步，后面又传来袁晶晶怒斥的声音：“跑什么跑？”李睿愕然，回头望去，委屈的道：“我没跑啊。”袁晶晶臻首高抬，露出白玉一般修长的脖颈，如同白天鹅一般高傲的走过来，脸色不善的瞧着他，鄙夷的道：“房间钥匙还在我这里，你跑回去又能开得了门？都多大的人了，办事还是这么慌里慌张、毛手毛脚，真不知道你是怎么在局里混下去的？哼，真是人头猪脑。”\n" +
//                        "　　在机关待过的人都知道，不论是上下级还是平级关系，哪怕彼此之间矛盾再深，也不会轻易在表面上露出来，平时都是和和气气好同志的模样，背地里才会给小鞋捅刀子。像袁晶晶这样当面辱骂李睿，可想而知，两人之间的龌龊深厚到了何种地步。\n" +
//                        "　　李睿恨得牙痒痒，却也没法反驳，心想，这贱人叫住自己斥骂一顿，无非是想摆领导派头，要走在前面，那自己就满足她，于是闷声不响的闪到一边。\n" +
//                        "　　袁晶晶这才满意，跟后面送出来的人们一一招呼话别，迈步当先走去。李睿如同一个听候使唤的小厮，垂着头弯着腰，跟在她屁股后面，亦步亦趋走向客房区。\n" +
//                        "　　这是七月的天，袁晶晶穿着一袭杏黄色短裙。这裙子面料又薄又软，极富弹性，裹在她的身子上，越发衬得她曲线玲珑。李睿跟在她身后，目光盯在她身上，只看得暗生口涎，心里暗想，要是能拥有这样一个老婆，这辈子给她踩着也认了。\n" +
//                        "　　来到客房区门口上台阶的时候，袁晶晶或许因为喝多了酒，居然踩了个空，一下子扑倒在台阶上，摔得要多狼狈有多狼狈。跟在她后面的李睿看到这一画面，立时幸灾乐祸的笑出来。还好他有分寸没笑出声，要不然袁晶晶很可能会迁怒到他身上。\n" +
//                        "　　袁晶晶这下摔得不轻，捂着左小腿“哎哟……啊……”的叫起疼来，不时发出倒吸凉气的声音，回头见李睿表情古怪的瞧着她，恨恨的骂道：“你眼瞎了呀？不会扶我一把啊？你是不是男人啊？”\n" +
//                        "　　李睿被骂得脸色讪讪，心想，老子是不是男人，你要试过才知道，悻悻的走上前，抓住她的胳膊，将她搀扶起来。\n" +
//                        "　　美女就是美女，手臂腻滑如同涂了银粉的美玉，手摸在上面，滑在心里，李睿整个人似乎飘上了天。\n" +
//                        "　　袁晶晶被扶起来站直身子后，却没动步，目光冷冷的看向李睿。李睿纳闷，问道：“又怎么了？”袁晶晶冷冰冰的说：“你手！”李睿看了下自己的手，正扶着她的胳膊，道：“我手在这啊，怎么了？”袁晶晶就好像看着一只恶心的苍蝇趴在自己身上似的，厌恶的说：“给我放开！”\n" +
//                        "　　李睿大怒，心想，刚扶你起来就给我玩卸磨杀驴，这种事也就只有你袁晶晶才干得出来，忘恩负义的贱人！脸上却不敢现出任何异色，乖乖的收回手去，站得远远的。\n" +
//                        "　　袁晶晶从他脸上收回鄙夷的目光，这才迈步，但也就是刚迈出第二步，就“哎哟”一声吃痛，左腿一哆嗦，差点没扑倒在地，整个人萎缩在那，叫道：“扶住我，李睿，快扶住我，好疼……”\n" +
//                        "　　李睿心说活该，让你逞强，却又不敢怠慢，上前扶住她。袁晶晶叫苦说：“哎哟，我走不动，一动就疼，你扶我回去。”李睿嗯了一声。\n" +
//                        "　　李睿直把袁晶晶扶到她房间床上，仔细观察了她左小腿的伤处，在薄薄肉色丝袜的掩映下，她秀美的小腿中段似乎磕破了皮，渗出了丝丝血迹。这处轻伤的存在，让她那双迷人的**在美观程度上大打折扣。\n" +
//                        "　　可尽管如此，李睿还是看得如痴如醉，毕恭毕敬的说：“主任，我有创口贴，我帮你把伤口贴上吧？”袁晶晶不屑的白他一眼，道：“在我面前装好人？你是什么东西，我心里清楚着呢。别以为我不知道，你想趁机揩我的油，这种把戏我见得多了！哼，以为我喝多了就有机可乘，是你白痴啊还是当我白痴，人头猪脑……”"));


//        Flux<String> flux = openAiChatModel.stream(new UserMessage("你好"));
//
//        flux.subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        });

//        OpenAiChatModel openAiChatModel = new OpenAiChatModel(new OpenAiApi(baseUrl, apikey),
//                OpenAiChatOptions.builder().withModel("qwen-plus").build());
        OpenAiImageModel openAiImageModel = new OpenAiImageModel(new OpenAiImageApi(baseUrl, apikey, RestClient.builder()), OpenAiImageOptions.builder().withModel("FLUX-dev").build(), new RetryTemplate());
        ImageResponse imageResponse = openAiImageModel.call(new ImagePrompt("抱着狗熊的小女孩"));
        ImageGeneration result = imageResponse.getResult();
        byte[] decode = Base64.getDecoder().decode(result.getOutput().getB64Json());
        FileOutputStream fileOutputStream = new FileOutputStream("./test.jpg");
        fileOutputStream.write(decode);
        fileOutputStream.close();

        System.out.println(result.getOutput().getB64Json());

    }

}
