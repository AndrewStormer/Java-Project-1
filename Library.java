import java.util.*;


enum Section {
    ARTS,
    SCIENCES,
    NEWSPAPERS,
    LAWS;
}


public class Library {
    public Hashtable<Integer, LibraryCollection> artSection; //hashtable keyed using item.title.hashCode()
    public Hashtable<Integer, LibraryCollection> scienceSection; //this can be thought of as bookshelves in different sections of the library
    public Hashtable<Integer, LibraryCollection> newspaperSection;
    public Hashtable<Integer, LibraryCollection> lawSection;

    private Hashtable<Integer, Member> members;
    private Hashtable<Integer, Employee> employees;

    //ondutyTechnician controls adding books, ondutyTechnican handles renting books
    public Librarian ondutyLibrarian;
    public Technician ondutyTechnician;

    Library() {
        this.artSection = new Hashtable<Integer, LibraryCollection>();
        this.scienceSection = new Hashtable<Integer, LibraryCollection>();
        this.newspaperSection = new Hashtable<Integer, LibraryCollection>();
        this.lawSection = new Hashtable<Integer, LibraryCollection>();

        this.members = new Hashtable<Integer, Member>();
        this.employees = new Hashtable<Integer, Employee>();

        this.setLibrarian(null);
        this.setTechnician(null);
    }
    Library(Librarian librarian, Technician technician) {
        this.artSection = new Hashtable<Integer, LibraryCollection>();
        this.scienceSection = new Hashtable<Integer, LibraryCollection>();
        this.newspaperSection = new Hashtable<Integer, LibraryCollection>();
        this.lawSection = new Hashtable<Integer, LibraryCollection>();
        
        this.members = new Hashtable<Integer, Member>();
        this.employees = new Hashtable<Integer, Employee>();

        this.setLibrarian(librarian);
        this.setTechnician(technician);
    }

    public void addMember(Member member) {
        this.members.put(member.name.hashCode(), member);
    }
    public Member getMember(String name) {
        return this.members.get(name.hashCode());
    }
    public void AddEmployee(Employee employee) {
        this.employees.put(employee.name.hashCode(), employee);
    }
    public Employee getEmployee(String name) {
        return this.employees.get(name.hashCode());
    }
    public void setLibrarian(Librarian librarian) {
        this.ondutyLibrarian = librarian;
    }
    public Librarian getLibrarian() {
        return this.ondutyLibrarian;
    }
    public void setTechnician(Technician technician) {
        this.ondutyTechnician = technician;
    }
    public Technician getTechnician() {
        return this.ondutyTechnician;
    }
    public void addLibrarian(Librarian librarian) {
        if ((this.employees.contains(librarian)) == false) {
            this.AddEmployee(librarian);
        }
        librarian.libraryToSupervise = this;
        if (this.ondutyLibrarian == null) {
            this.setLibrarian(librarian);
            return;
        }
    }
    public void addTechnician(Technician technician) {
        if ((this.employees.containsKey(technician.name.hashCode())) == false) {
            this.AddEmployee(technician);
        }
        technician.libraryToSupervise = this;
        if (this.ondutyLibrarian == null) {
            this.setTechnician(technician);
            return;
        }
    }
    public LibraryCollection getItem(Section section, String name) {
        switch (section) {
            case ARTS:
                return this.artSection.get(name.hashCode());
            case SCIENCES:
                return this.scienceSection.get(name.hashCode());       
            case NEWSPAPERS:
                return this.newspaperSection.get(name.hashCode());
            case LAWS:
                return this.lawSection.get(name.hashCode());  
            default:
                if (this.artSection.containsKey(name.hashCode()))
                    return this.artSection.get(name.hashCode());
                else if (this.scienceSection.containsKey(name.hashCode()))
                    return this.scienceSection.get(name.hashCode());    
                if (this.newspaperSection.containsKey(name.hashCode()))
                    return this.newspaperSection.get(name.hashCode());
                else 
                    return this.lawSection.get(name.hashCode());      
        }
    }
}
