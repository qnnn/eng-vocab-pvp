package com.eng.client.message;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.eng.client.dto.CompetitorWord;
import com.eng.client.service.WordService;
import lombok.Data;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/handler/{sno}")
@Component
public class WebSocketServer {

    static Log log= LogFactory.get(WebSocketServer.class);
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static final ConcurrentHashMap<String, WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /** 安全set，存放未开始的玩家 */
    private static final CopyOnWriteArraySet<String> waiting = new CopyOnWriteArraySet<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收sno*/
    private String sno="";

    /**记录当前玩家*/
    private Player currentPlayer;

    /**记录当前题目索引*/
    private int subjectIndex;

    /**答对题目数量*/
    private int correctNum = 0;

    private static WordService wordService;

    @Autowired
    public void setWordService(WordService wordService){
        WebSocketServer.wordService = wordService;
    }



    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("sno") String sno, Session session) {

        this.session = session;
        this.sno = sno;

        createGame(sno);

        if(webSocketMap.containsKey(sno)){
            webSocketMap.remove(sno);
            webSocketMap.put(sno,this);
            //加入set中
        }else{
            webSocketMap.put(sno,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }



        log.info("用户连接:"+sno+",当前在线人数为:" + getOnlineCount());

        try {
            if (!"".equals(this.currentPlayer.getOpponent())&&this.currentPlayer.getOpponent()!=null){

                List<CompetitorWord> subjects = wordService.getSubject();
                // 设置同一套题
                this.currentPlayer.setProblems(subjects);
                webSocketMap.get(this.currentPlayer.getOpponent()).currentPlayer.setProblems(subjects);

                // 通知双方开始
                sendMessage("begin");
                webSocketMap.get(this.currentPlayer.getOpponent()).sendMessage("op.begin");
            }
        } catch (IOException e) {
            log.error("用户:"+sno+",网络异常!!!!!!");
        }
    }

    /**
     * 建立对局，应是原子性的操作
     * @param sno
     */
    private synchronized void createGame(String sno){

        Iterator<String> iterator = waiting.iterator();
        if (iterator.hasNext()){
            String opponentPlayer = iterator.next();
            if (opponentPlayer.equals(sno))
            {
                return;
            }
            waiting.remove(opponentPlayer);
            Player player = new Player();
            player.setSno(sno);
            player.setOpponent(opponentPlayer);
            this.currentPlayer = player;


            log.info("对局建立");

            webSocketMap.get(opponentPlayer).currentPlayer.setOpponent(sno);

            try {
                // 通知
                webSocketMap.get(opponentPlayer).sendMessage("我是: " + this.sno + "发送给"+ currentPlayer.getOpponent());

            } catch (IOException e) {
                try {
                    sendMessage("对方已下线");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                webSocketMap.remove(sno);
                e.printStackTrace();
            }

        }else {
            waiting.add(sno);
            Player player = new Player();
            player.setSno(sno);
            player.setOpponent("");
            this.currentPlayer = player;
            try {
                sendMessage("正在匹配对局玩家");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Data
    static class Player{
        private String sno;
        private String opponent;
        private List<CompetitorWord> problems;
    }


    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(sno)){
            if (!"".equals(this.currentPlayer.getOpponent())&&this.currentPlayer!=null&&webSocketMap.get(this.currentPlayer.getOpponent())!=null){
                try {
                    webSocketMap.get(this.currentPlayer.getOpponent()).sendMessage("opponent:exit");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            webSocketMap.remove(sno);
            waiting.remove(sno);
            waiting.remove(currentPlayer.getOpponent());
            //从set中删除
            subOnlineCount();
        }
        log.info("用户退出:"+sno+",当前在线人数为:" + getOnlineCount());

    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("用户消息:"+sno+",报文:"+message);
        // 获取题目
        if (message.startsWith("getSubject:")){
            int index = Integer.parseInt(message.substring(11));

            // 题目答完
            if (index == 10){
                int opCorrectNum = webSocketMap.get(this.currentPlayer.getOpponent()).correctNum;
                if (this.correctNum>opCorrectNum){
                    try {
                        sendMessage("win");
                        webSocketMap.get(this.currentPlayer.getOpponent()).sendMessage("lose");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else if (this.correctNum<opCorrectNum){
                    try {
                        sendMessage("lose");
                        webSocketMap.get(this.currentPlayer.getOpponent()).sendMessage("win");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        sendMessage("tie");
                        webSocketMap.get(this.currentPlayer.getOpponent()).sendMessage("tie");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                log.info("用户："+ this.sno+ "， 答对题目数量为：" + this.correctNum);
                log.info("用户："+ this.currentPlayer.getOpponent()+ "， 答对题目数量为：" + webSocketMap.get(this.currentPlayer.getOpponent()).correctNum);
                log.info(this.currentPlayer.getProblems().toString());

                return;
            }

            CompetitorWord competitorWord = this.currentPlayer.getProblems().get(index);
            String res = wordService.doConfuse(competitorWord,index);

            // 当前题目索引
            this.subjectIndex = index;
            webSocketMap.get(this.currentPlayer.getOpponent()).subjectIndex = index;
            try {
                sendMessage(res);
                webSocketMap.get(this.currentPlayer.getOpponent()).sendMessage("op."+res);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else if (message.startsWith("res:")){
            String res = message.substring(4);
            if (this.currentPlayer.getProblems().get(this.subjectIndex).getDefinition().equals(res)){
                try {
                    this.correctNum++;
                    sendMessage("correct");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    sendMessage("wrong");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("用户错误:"+this.sno+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,String sno) throws IOException {
        log.info("发送消息到:"+sno+"，报文:"+message);
        if(StringUtils.isNotBlank(sno)&&webSocketMap.containsKey(sno)){
            webSocketMap.get(sno).sendMessage(message);
        }else{
            log.error("用户"+sno+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}
