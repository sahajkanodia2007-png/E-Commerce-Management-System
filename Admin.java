public class Admin extends Person {
    public Admin(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String getRole() { return "Admin"; }
}
