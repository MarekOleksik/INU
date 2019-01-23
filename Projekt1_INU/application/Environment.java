package application;

public enum Environment {
 Development("Deweloperskie"),
 Test("Testowe"),
 Production("Produkcyjne");
 private String name;
 Environment(String name) {
 this.name = name;
 }
 @Override
 public String toString() {
 return name;
 }
}