//Time complexity of below program will be O(n) where n is number of packets or number of lines in CSV
// because it is iterating on each line of CSV to check that packet is valid or not and all other operations are done in constant time
// space complexity - O(1) because it is using constant space to store comma separated value of each line
import java.io.BufferedReader;
import java.io.FileReader;

public class Firewall
{
    String CSVFile;
    //Constructor for firewall class which will take input as CSVfile
    public Firewall(String CSVFile) {
        this.CSVFile = CSVFile;
    }

    //Function which read packets from CSV and check that packets are acceptable or not using function accept_packet
    void readPackets()
    {
        //Read CSV of packets line by line using bufferreader and process each line to check packet is acceptable or not
        try (BufferedReader br = new BufferedReader(new FileReader(CSVFile))) {
            String line;
            //Iterate line by line in CSV and get each packet
            while ((line = br.readLine()) != null) {
                //split each packet(or line) using comma and store each value in array
                String[] values = line.split(",");

                //declare variables to store values from array
                String direction = values[0];
                String protocol=values[1];
                String port=values[2];
                String ip_address=values[3];

                //Check if port is not integer and it contains range
                if(port.contains("-") && !port.matches("-?[1-9]\\d*|0"))
                {
                    //Split port using "-" and store its value in array
                    String[] str= port.split("-");
                    //Check that both start range and end range of port is valid and check packet is valid
                    System.out.println(accept_packet(direction,protocol,Integer.valueOf(str[0]),ip_address)
                            && accept_packet(direction,protocol,Integer.valueOf(str[1]),ip_address));
                }
                else
                    //If port is an integer then check packet is acceptable or not
                    System.out.println(accept_packet(direction,protocol,Integer.valueOf(port),ip_address));
            }
        }
        catch (Exception e)
        {
            e.getCause();
        }
    }

    //Function to check valid ipV4 address using regex
    boolean isValidIpV4(String IP)
    {
        String ipv4regex = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        if (IP.contains("-"))
        {
            String[] str = IP.split("-");
            if (str[0].matches(ipv4regex) && str[1].matches(ipv4regex))
                return true;
        }
        else if(IP.matches(ipv4regex))
            return true;
        return false;
    }

    //Function to check valid port
    boolean isValidPort(int port)
    {
        if ((port>=1 && port<=65535))
            return true;
        return false;
    }

    //Function to check valid direction
    boolean isValidDirection(String direction)
    {
        return direction.equals("inbound") || direction.equals("outbound");
    }

    //Function to check valid protocol name
    boolean isValidProtocolName(String protocol)
    {
        return protocol.equals("tcp") || protocol.equals("udp");
    }

    //Function to check valid packet or not
    boolean accept_packet(String direction, String protocol, int port, String ip_address)
    {
        if (isValidDirection(direction)
                && isValidProtocolName(protocol)
                && (isValidPort(port))
                && (isValidIpV4(ip_address)))
        {
           return true;
        }

        return false;
    }

    public static void main(String[] args) {
        //Created firewall object and initialized it with CSV file
        Firewall firewall = new Firewall("/Users/imrvshah/Documents/JavaProgram/HostBasedFirewall/testCase1.csv");
        firewall.readPackets();
    }
}
