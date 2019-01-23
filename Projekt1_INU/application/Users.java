package application;

import java.util.ArrayList;
import java.util.HashMap;

public class Users {
 static Users usrInstance;
 public static Users getInstance() {
 if (usrInstance == null)
 usrInstance = new Users();
 return usrInstance;
 }
 private Users() {
 users = new HashMap<Environment, HashMap<String, String>>();
 readUsers();
 }
 private HashMap<Environment, HashMap<String, String> > users;
 public ArrayList<String> getUsers(Environment env) {
 return new ArrayList<String>(users.get(env).keySet());
 }
 public boolean isPassCorrect(Environment env, String userId,
 String pass) {
 String pass1 = users.get(env).get(userId);
 if (pass1 == null) return false;
 return pass1.equals(pass);
 }
 private void readUsers() {
 HashMap<String,String> devUsers = new HashMap<String, String>();
 devUsers.put("maciej.oleksik", "1234");
 devUsers.put("jakub.oleksik", "1234");
 devUsers.put("szymon.oleksik", "1234");
 users.put(Environment.Development, devUsers);
 HashMap<String,String> testUsers = new HashMap<String, String>();
 testUsers.put("ewa.cudna", "1234");
 testUsers.put("adam.nowak", "1234");
 testUsers.put("jan.kowalski", "1234");
 users.put(Environment.Test, testUsers);
 HashMap<String, String> prodUsers = new HashMap<String, String>();
 prodUsers.put("jan.kowalski", "1234");
 prodUsers.put("artur.nowak", "1234");
 prodUsers.put("tomasz.barul", "1234");
 users.put(Environment.Production, prodUsers);
 }
}