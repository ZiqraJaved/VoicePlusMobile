package com.iteam.voiceplusmobile.ui.chatbox;

import androidx.lifecycle.ViewModelProviders;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.applozic.mobicomkit.Applozic;
import com.applozic.mobicomkit.ApplozicClient;
import com.applozic.mobicomkit.api.account.register.RegistrationResponse;
import com.applozic.mobicomkit.api.account.user.User;
import com.applozic.mobicomkit.feed.TopicDetail;
import com.applozic.mobicomkit.listners.AlLoginHandler;
import com.applozic.mobicomkit.uiwidgets.ApplozicSetting;
import com.applozic.mobicomkit.uiwidgets.async.ApplozicConversationCreateTask;
import com.applozic.mobicomkit.uiwidgets.conversation.ConversationUIService;
import com.applozic.mobicomkit.uiwidgets.conversation.activity.ConversationActivity;
import com.applozic.mobicommons.ALSpecificSettings;
import com.applozic.mobicommons.people.channel.Conversation;
import com.iteam.voiceplusmobile.HelperContent;
import com.iteam.voiceplusmobile.R;

import java.util.HashMap;
import java.util.Map;


public class ChatBoxFragment extends Fragment {

    private ChatBoxViewModel mViewModel;

    public static ChatBoxFragment newInstance() {
        return new ChatBoxFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat_box, container, false);
        Applozic.init(getContext(), "141cdaaf1132b265ec2c1ad2b39dc8a5c");
        init_chat_box();

        return view;

    }

    private void init_chat_box() {
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMessage("Loading your chat history and preferences.");
        progressDoalog.setTitle("Please Wait");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();

        boolean result = user_registration();

        if (!result) {
            Toast.makeText(getActivity().getBaseContext(), "Please click again on chat box menu!", Toast.LENGTH_LONG).show();

        } else {


        }
        start_customer_chat();
//        startActivity(intent);
//        ApplozicSetting.getInstance(getContext()).back;

        progressDoalog.dismiss();

    }

    private Conversation buildConversation() {


        TopicDetail topic = new TopicDetail();
        topic.setTitle("Voice Plus Mobile Helpline");//Your Topic title
        topic.setSubtitle("Please discus your problem here! ");//Put Your Topic subtitle


        Conversation conversation = new Conversation();

        //SET UserId for which you want to launch chat or conversation

        conversation.setTopicId("1_" + HelperContent.getUser_phone_number());
        conversation.setUserId("1");
        conversation.setTopicDetail(topic.getJson());
        return conversation;

    }


    private boolean user_registration() {
        final boolean[] _result = {false};
        User user = new User();
        user.setUserId(HelperContent.getUser_phone_number());
        user.setDisplayName(HelperContent.getUser_real_name());
        user.setAuthenticationTypeId(User.AuthenticationType.APPLOZIC.getValue());
        user.setPassword("");
        user.setImageLink("");
        Applozic.connectUser(getContext(), user, new AlLoginHandler() {
            @Override
            public void onSuccess(RegistrationResponse registrationResponse, Context context) {
                // After successful registration with Applozic server the callback will come here

                System.out.println("kkkkkk");
                System.out.println(registrationResponse.getMessage());
                _result[0] = true;

            }

            @Override
            public void onFailure(RegistrationResponse registrationResponse, Exception exception) {
                // If any failure in registration the callback  will come here

                System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                System.out.println(exception.getMessage());
                _result[0] = false;
            }
        });


        return _result[0];
    }


    private void start_customer_chat() {
        if (!Applozic.isConnected(getContext())) {
            user_registration();
        }
        if (Applozic.isConnected(getContext())) {
            ApplozicConversationCreateTask applozicConversationCreateTask = null;

            ApplozicConversationCreateTask.ConversationCreateListener conversationCreateListener = new ApplozicConversationCreateTask.ConversationCreateListener() {
                @Override
                public void onSuccess(Integer conversationId, Context context) {

                    //For launching the  one to one  chat
                    Intent intent = new Intent(context, ConversationActivity.class);
                    intent.putExtra("takeOrder", true);
                    intent.putExtra(ConversationUIService.USER_ID, "1");//RECEIVER USERID
                    intent.putExtra(ConversationUIService.DISPLAY_NAME, "Admin");
                    intent.putExtra(ConversationUIService.CONTEXT_BASED_CHAT, true);
                    intent.putExtra(ConversationUIService.CONVERSATION_ID, conversationId);

                    Map<String, String> messageTemplates = new HashMap<>();
                    messageTemplates.put("Greetings", "Hey there!");

                    messageTemplates.put("Share my userId", "Hey there my userId is : " + HelperContent.getUser_phone_number());
//                    ApplozicSetting.getInstance(context).setDeleteConversationOption(false);
//                    ApplozicSetting.getInstance(context).setHideGroupAddButton(false);
//                    ApplozicSetting.getInstance(context).disableMessageSearch();
//                    ApplozicSetting.getInstance(context).disableProfileOption();
//                    ApplozicSetting.getInstance(context).hideStartNewButton();
//                    ApplozicSetting.getInstance(context).;
//                    ApplozicSetting.getInstance(context).setHideGroupAddButton(false);
//                    ALSpecificSettings.getInstance(context).enableTextLogging(true);
//                    ALSpecificSettings.getInstance(context).setL

                    ApplozicClient.getInstance(context).setMessageTemplates(messageTemplates);
                    startActivity(intent);
                }

                @Override
                public void onFailure(Exception e, Context context) {

                }
            };
            Conversation conversation = buildConversation(); //From Step 1
//conversation.
            applozicConversationCreateTask = new ApplozicConversationCreateTask(getContext(), conversationCreateListener, conversation);

            applozicConversationCreateTask.execute((Void) null);
            Intent intent = new Intent(getContext(), ConversationActivity.class);

//            ApplozicSetting.getInstance(context).setDeleteConversationOption(true);

            startActivity(intent);
//            Toast.makeText(getActivity().getBaseContext(), "User login", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ChatBoxViewModel.class);
        // TODO: Use the ViewModel
    }

}