package com.example.bluestarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    private ListView questionListView;
    private ListView messageListView;
    private EditText editTextMessage;
    private List<String> questions;
    private ArrayAdapter<String> questionAdapter;
    private List<String> messages;
    private ArrayAdapter<String> messageAdapter;
    ImageView imageViewReturn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        questionListView = findViewById(R.id.questionListView);
        messageListView = findViewById(R.id.messageListView);
        editTextMessage = findViewById(R.id.editTextMessage);
        imageViewReturn = findViewById(R.id.imageViewReturn);

        // Danh sách câu hỏi để hiển thị trên màn hình
        questions = new ArrayList<>();
        questions.add("Quyền lợi khi trở thành khách hàng của BlueStar?");
        questions.add("Chính sách hoàn trả vé của BlueStar?");
        questions.add("Chương trình khuyến mãi thường diễn ra vào thời gian nào?");

        questionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questions);
        questionListView.setAdapter(questionAdapter);
//        ListView listView = findViewById(R.id.yourListViewId);


        messages = new ArrayList<>();
        messageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messages);
        messageListView.setAdapter(messageAdapter);

        questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedQuestion = questions.get(position);
                String reply = getReplyForQuestion(selectedQuestion);

                // Hiển thị câu hỏi và câu trả lời trong danh sách
                messages.add(selectedQuestion);
                messages.add(reply);
                messageAdapter.notifyDataSetChanged();
                messageListView.smoothScrollToPosition(messageAdapter.getCount() - 1);
            }
        });

        editTextMessage.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                String newMessage = editTextMessage.getText().toString().trim();
                if (!newMessage.isEmpty()) {
                    // Thêm câu hỏi từ người dùng
                    messages.add(newMessage);

                    // Xử lý câu trả lời dựa trên câu hỏi
                    String reply = getReplyForQuestion(newMessage);
                    messages.add(reply);

                    // Cập nhật giao diện
                    messageAdapter.notifyDataSetChanged();
                    messageListView.smoothScrollToPosition(messageAdapter.getCount() - 1);
                    editTextMessage.getText().clear();
                }
                return true;
            }
            return false;
        });

        imageViewReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myitent = new Intent(Chat.this, MainActivity.class);
                startActivity(myitent);
            }
        });

    }

    private String getReplyForQuestion(String question) {
        // Xử lý câu hỏi và trả lời tương ứng
        switch (question) {
            case "Quyền lợi khi trở thành khách hàng của BlueStar?":
                return "Chuyến bay an toàn, được tích điểm.";
            case "Chính sách hoàn trả vé của BlueStar?":
                return "Chúng tôi không hoàn trả trong trường hợp hủy vé";
            case "Chương trình khuyến mãi thường diễn ra vào thời gian nào?":
                return "Bạn hãy đợi nhé";
            default:
                return "Với câu hỏi này, vui lòng liên hệ hotline của BlueStar để được tư vấn cụ thể hơn. Xin cảm ơn quý khách!";
        }
    }
}
