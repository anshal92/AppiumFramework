package test.api_test.request_response_data;

public class GetUserDataDTO {
    private String email;

    private String name;

    private double time;

    public void setEmail(String email){
        this.email = email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setTime(double time){
        this.time = time;
    }
    public double getTime(){
        return this.time;
    }
}
