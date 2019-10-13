# Firewall
Implementation of predefined firewall rules which determine whether traffic should be allowed or blocked.

Time complexity - O(n) where n is number of packets or number of lines in CSV because it is iterating on each line of CSV 
to check that packet is valid or not and all other operations are done in constant time.
Space complexity - O(1) because it is using constant space to store comma separated value of each line. 

I have tested this code using below test cases. 
1. I have tested this code with null value for direction and protocol, Negative value of port, Out of range(1-65535) value of port, Out of
range(0.0.0.0-255.255.255.255) value of Ip address. (used CSV testCase1 for this test)
2. I have tested this code with large CSV having around 1 M records. (used CSV testCase2 for this test) 

To make code clean and optimized I have created separate validation functions for all four parameters(direction,protocol,port,Ip address). 
To optimize function isValidIpV4 I have used regex to validate Ipv4 address.

My preference for team is below :
1. Policy Team
2. Data Team
3. Platform Team
