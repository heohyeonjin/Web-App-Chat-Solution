package com.example.chattingapp.ui

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chattingapp.MyFirebaseMessagingService
import com.example.chattingapp.R
import com.example.chattingapp.adapter.ChatRoomAdapter
import com.example.chattingapp.data.model.Chat
import com.example.chattingapp.data.model.EnterDTO
import com.example.chattingapp.data.service.ChatApiService
import com.example.chattingapp.databinding.ActivityChatBinding
import com.example.chattingapp.ui.chat.ChatViewModel
import com.example.chattingapp.ui.chat.ChatViewModelFactory
import com.example.chattingapp.utils.NetworkConnection
import com.example.chattingapp.utils.NetworkStatus
import com.example.chattingapp.utils.toast
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class ChatActivity : AppCompatActivity() {

    lateinit var fcm : MyFirebaseMessagingService
    lateinit var viewAdapter : ChatRoomAdapter
    val datas = ArrayList<Chat>()
    private lateinit var ChatActivityRecycleview : RecyclerView
    private lateinit var binding: ActivityChatBinding
    lateinit var viewModel : ChatViewModel
    lateinit var chatViewModelFactory: ChatViewModelFactory
    val TAG = "msg"
    lateinit var enterDTO : EnterDTO




    companion object{
        var mChatActivity:ChatActivity? = null
        var mFirebaseMsg:MyFirebaseMessagingService? = null
        var TAG = ""
        val CHAT_IN_MSG = 100
        var mHandler_Chat = @SuppressLint("HandlerLeak")

        object:Handler(){
            override fun handleMessage(msg: Message) {
                var _sel  = msg.what
                when(_sel){
                    CHAT_IN_MSG -> {
                        ChatApiService.instance.getChatList(mChatActivity!!.enterDTO.roomNo) {
                            mChatActivity!!.dataChangeAndScrollToEnd(it)
                        }

//                        else{
//                            mFirebaseMsg!!.sendNotification(mFirebaseMsg!!.title,
//                                mFirebaseMsg!!.body!!, mFirebaseMsg!!.pendingIntent!!, mFirebaseMsg!!.roomNo!!.toInt())
//
//                        }
                    }
                }
            }
        }


    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        val newdata: EnterDTO = intent!!.getParcelableExtra("EnterDTO")!!
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(newdata.roomNo.toInt())
        Log.d(TAG, "msgdata : $newdata")
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        var chatCompanyName = findViewById<TextView>(R.id.chat_name)
        var endButton = findViewById<Button>(R.id.chat_back)

        mChatActivity = this

        val connection = NetworkConnection(applicationContext)
        connection.observe(this){ isConnected ->
            if (isConnected) NetworkStatus.status = true
            else NetworkStatus.status = false
        }


//        Thread {
//            fcm.onMessageReceived().
////                .runCatching {  }.isSuccess
//        }

        //?????????
        enterDTO = EnterDTO(0L, "", 0L)

        if (intent.hasExtra("EnterDTO")) {
            enterDTO = intent.getParcelableExtra("EnterDTO")!!
            chatCompanyName?.text = enterDTO.corpName
            Log.d("????????? ?????????", "enterDTO.corpName & roomNo = " + enterDTO.corpName + enterDTO.roomNo)

            // ?????? ?????? ?????? ????????? ?????? ????????? -> ?????? ?????? ????????????
            if (enterDTO.roomNo != 0L) {
                ChatApiService.instance.getChatList(enterDTO.roomNo) {
                    dataChangeAndScrollToEnd(it)
                }
            }
        }
        if (intent.getIntExtra("GetFCM", 0) == 1) {
            Log.d("fcm ??????", "intent 1??? ?????????")
            enterDTO = intent.getParcelableExtra("EnterDTO")!!
            chatCompanyName?.text = enterDTO.corpName
            ChatApiService.instance.getChatList(enterDTO.roomNo) {
                dataChangeAndScrollToEnd(it)
            }
        }

        // notification ?????? ??????
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(enterDTO.roomNo.toInt())

        viewAdapter = ChatRoomAdapter(datas, chatCompanyName.text.toString())
        ChatActivityRecycleview = findViewById(R.id.chat_content)
        ChatActivityRecycleview.adapter = viewAdapter
        ChatActivityRecycleview.layoutManager= LinearLayoutManager(applicationContext)
        ChatActivityRecycleview.setHasFixedSize(true)
        initViewModel()

        // ?????? ?????? ????????? ???
        endButton.setOnClickListener {
            finish()
        }
    }

    private fun initViewModel(){
        chatViewModelFactory = ChatViewModelFactory()
        viewModel = ViewModelProvider(this, chatViewModelFactory).get(ChatViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this

        // ?????? ??????
        viewModel.corpNo = enterDTO.corpNo
        viewModel.getResponse.observe(this) {
            var sendRoomNo = it

            if (sendRoomNo == 0L) {
                toast("?????? ?????? ????????? ???????????? ???????????? ????????????.")
                finish()
            }

            Log.d("????????????", "roomNo: " + it + ", corpNo : " + enterDTO.corpNo + ", sendRoomNo : " + sendRoomNo)
            //?????? ?????? ??????
            ChatApiService.instance.getChatList(sendRoomNo) {
                dataChangeAndScrollToEnd(it)
            }
        }
    }

    fun dataChangeAndScrollToEnd(messages: List<Chat>) {
        viewAdapter.setMessages(messages)
        if(viewAdapter.itemCount > 0)
            ChatActivityRecycleview.smoothScrollToPosition(viewAdapter.itemCount - 1)
    }
}