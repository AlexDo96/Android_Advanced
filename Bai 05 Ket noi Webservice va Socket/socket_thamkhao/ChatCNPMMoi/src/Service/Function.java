package Service;

public interface Function {
	  int LOGIN = 1;
	  int LOGIN_SUCCESS = 2;
	  int LOGIN_FAIL = 3;
	  int LOGOUT = 4;	  
	  int SEND_MESSAGE = 5;
	  int RECIEVE_MESSAGE=6;
	  int SEND_MESSAGE_CHAT_ROOM=7;
	  int RECEIVE_MESSAGE_CHAT_ROOM=8;
	  int ONLINE_USER=9;
	  int OFFLINE_USER=10;
	  int GET_ONLINE_USER_LIST=11;
}
