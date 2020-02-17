package com.ssafy.edu.vue.service;

import java.util.List;

import com.ssafy.edu.vue.dto.Message;

public interface IMessageService {

	List<Message> getMessages(int memberid);

	List<Message> getSendMessages(int memberid);
	
	Message getMessage(int id);

	void addMessage(Message message);

	void readMessage(int id);

	int checkUnread(int memberid);

}
