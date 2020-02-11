package WebLogProgram;
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;
import WebLogProgram.LogEntry;


public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             LogEntry currentLogEntry = WebLogParser.parseEntry(line);
             records.add(currentLogEntry);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

     public int countUniqueIPs() {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             if (!uniqueIPs.contains(ipAddress)) {
                 uniqueIPs.add(ipAddress);
             }
         }
         return uniqueIPs.size();
     }

     public void printAllHigherThanNum(int num) {
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode>num) {
                 System.out.println(le);
             }
         }
     }

     public ArrayList<String> uniqueIPVisitsOnDay(String someday) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             String fullDate = le.getAccessTime().toString();
             String day = fullDate.substring(4,10);
             //System.out.println("Day is "+day);
             String ipAddress = le.getIpAddress();
             if (day.equals(someday) && !uniqueIPs.contains(ipAddress)) {
                 uniqueIPs.add(ipAddress);
             }
         }
         return uniqueIPs;
     }

     public int countUniqueIPsInRange(int low, int high) {
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode>=low && statusCode<=high) {
                 String ipAddress = le.getIpAddress();
                 if (!uniqueIPs.contains(ipAddress)) {
                     uniqueIPs.add(ipAddress);
                 }
             }
         }
         return uniqueIPs.size();
     }

     public HashMap<String, Integer> countVisistsPerIP() {
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for (LogEntry le : records) {
             String ipAddress = le.getIpAddress();
             if (! counts.containsKey(ipAddress)) {
                 counts.put(ipAddress, 1);
             } else {
                 counts.put(ipAddress, counts.get(ipAddress)+1);
             }
         }
         return counts;
     }

     public int mostNumberVisitsByIP(HashMap<String, Integer> countsHashMap) {
         int max = 0;
         for (String ipAddress : countsHashMap.keySet()) {
             int currentCounts = countsHashMap.get(ipAddress);
             if (currentCounts > max) {
                 max = currentCounts;
             }
         }
         return max;
     }

     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> countsHashMap) {
         int maxVisits = mostNumberVisitsByIP(countsHashMap);
         ArrayList<String> mostVisitIPList = new ArrayList<>();
         for (String ip : countsHashMap.keySet()) {
             int currentVisitCounts = countsHashMap.get(ip);
             if (currentVisitCounts == maxVisits) {
                 mostVisitIPList.add(ip);
             }
         }
         return mostVisitIPList;
     }

     public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String, ArrayList<String>> dayToIPMap = new HashMap<String, ArrayList<String>>();
         for (LogEntry le : records) {
             String fullDate = le.getAccessTime().toString();
             String day = fullDate.substring(4,10);
             if (!dayToIPMap.containsKey(day)) {
                 ArrayList<String> currentIPArray = new ArrayList<String>();
                 String ip = le.getIpAddress();
                 currentIPArray.add(ip);
                 dayToIPMap.put(day, currentIPArray);
             } else {
                 ArrayList<String> currentIPArray = dayToIPMap.get(day);
                 currentIPArray.add(le.getIpAddress());
                 dayToIPMap.put(day, currentIPArray);
             }
         }
         return dayToIPMap;
     }

     public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dayToIPMap) {
         int mostVisits = 0;
         String dayWithMostVisits = "";
         for (String day : dayToIPMap.keySet()) {
             if (dayToIPMap.get(day).size() > mostVisits) {
                 mostVisits = dayToIPMap.get(day).size();
                 dayWithMostVisits = day;
             }
         }
         return dayWithMostVisits;
     }

     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dayToIPMap, String someDay) {
         ArrayList<String> ipsWithMostVisitsOnSomeDay = new ArrayList<>();
         HashMap<String, Integer> countsForSomeDayMap = new HashMap<>();
         ArrayList<String> uniqueIPVisitsOnSomeDay = uniqueIPVisitsOnDay(someDay);
         ArrayList<String> ipsForSomeDay = dayToIPMap.get(someDay);
         for (String ip : ipsForSomeDay) {
             if (! countsForSomeDayMap.containsKey(ip)) {
                 countsForSomeDayMap.put(ip, 1);
             } else {
                 countsForSomeDayMap.put(ip, countsForSomeDayMap.get(ip)+1);
             }
         }
         int mostNumberOfVisitsForSomeDay = mostNumberVisitsByIP(countsForSomeDayMap);
         for (String ip : uniqueIPVisitsOnSomeDay) {
             if (countsForSomeDayMap.get(ip).equals(mostNumberOfVisitsForSomeDay)) {
                 ipsWithMostVisitsOnSomeDay.add(ip);
             }
         }
         return ipsWithMostVisitsOnSomeDay;
     }
}
