package com.ssafy.edu.vue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ssafy.edu.vue.dao.MessageDaoImpl;
import com.ssafy.edu.vue.dto.Message;


@Service
public class MessageServiceImpl implements IMessageService {
	
	@Autowired
	private MessageDaoImpl messagedao;

	@Override
	@Transactional(readOnly=true)
	public List<Message> getMessages(int memberid) {
		return messagedao.getMessages(memberid);
	}

	@Override
	@Transactional(readOnly=true)
	public Message getMessage(int id) {
		return messagedao.getMessage(id);
	}

	@Override
	@Transactional
	public void addMessage(Message message) {
		messagedao.addMessage(message);
	}
}
