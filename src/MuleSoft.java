import java.sql.*;
import java.util.Scanner;

public class MuleSoft {
    public static void main(String args[])
    {
        try
        {
            Class.forName("org.sqlite.JDBC");
            Connection con=DriverManager.getConnection("jdbc:sqlite:product.db");
            Statement st= con.createStatement();
            String query= "create table if not exists Movies"+"(moviename varchar(200),"+"leadactor varchar(200),"+"actress varchar(200),"+"yearofrelease int,"+"directorname varchar(200));";
            st.executeUpdate(query);
            Scanner sc=new Scanner(System.in);
            System.out.println("1.insert data into table");
            System.out.println("2.retrieve");
            System.out.println("3.get movie name based on actor name");
            int choice =sc.nextInt();
            switch (choice)
            {
                case 1:
                    System.out.println("Give how many records should be inserted in the table");
                    int n=sc.nextInt();
                    for(int i=0;i<n;i++)
                    {
                        String moviename=sc.next();
                        String leadactor=sc.next();
                        String actress=sc.next();
                        int  yearofrelease=sc.nextInt();
                        String directorname=sc.next();
                        String sql="insert into Movies values(?,?,?,?,?)";
                        PreparedStatement preparedStatement=con.prepareStatement(sql);
                        preparedStatement.setString(1,moviename);
                        preparedStatement.setString(2,leadactor);
                        preparedStatement.setString(3,actress);
                        preparedStatement.setInt(4,yearofrelease);
                        preparedStatement.setString(5,directorname);
                        preparedStatement.executeUpdate();
                    }
                case 2:
                    String sql="select * from Movies;";
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next())
                    {
                        System.out.print("MovieName: "+rs.getString("moviename"));
                        System.out.print(", ActorName: "+rs.getString("leadactor"));
                        System.out.print(", Actress: "+rs.getString("actress"));
                        System.out.print(", YearOfRelease: "+rs.getInt("yearofrelease"));
                        System.out.println(", DirectorName: "+rs.getString("directorname"));
                    }
                case 3:

                    String s=sc.next();
                    String sq="select moviename from Movies where leadactor == ?";
                    PreparedStatement preparedStatement=con.prepareStatement(sq);
                    preparedStatement.setString(1,s);
                    ResultSet rset = preparedStatement.executeQuery();
                    while(rset.next())
                    {                    System.out.println(rset.getString("moviename"));
                    }}
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
