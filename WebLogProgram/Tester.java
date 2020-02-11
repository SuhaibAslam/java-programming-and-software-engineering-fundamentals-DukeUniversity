package WebLogProgram;
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import WebLogProgram.LogEntry;
import edu.duke.*;
import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        // complete method
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/short-test_log");
        la.printAll();
    }

    public void testUniqueIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        int uniqueIPs = la.countUniqueIPs();
        System.out.println("Number of unique IPs: "+uniqueIPs);
    }

    public void testPrintAllHigherThanNum() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/short-test_log");
        int num = 200;
        la.printAllHigherThanNum(num);
    }

    public void testUniqueIPVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        String someDay = "Sep 24";
        ArrayList<String> uniqueVisitsOnDayArray = la.uniqueIPVisitsOnDay(someDay);
        System.out.println("Number of Unique visits on "+someDay+" were "+uniqueVisitsOnDayArray.size());
    }

    public void testCountUniqueIPsInRange() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        int low = 200;
        int high = 299;
        int uniqueIPsInRange = la.countUniqueIPsInRange(low,high);
        System.out.println("Number of Unique IPs whose status codes are in range from "+low+" "+high+" are "+uniqueIPsInRange);
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/short-test_log");
        HashMap<String, Integer> counts = la.countVisistsPerIP();
        System.out.println(counts);
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        HashMap<String, Integer> countsHashMap = la.countVisistsPerIP();
        int maxVisitsByOneIP = la.mostNumberVisitsByIP(countsHashMap);
        System.out.println(maxVisitsByOneIP);
    }

    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        HashMap<String, Integer> countsHashMap = la.countVisistsPerIP();
        ArrayList<String> mostVisitedIPs = la.iPsMostVisits(countsHashMap);
        System.out.println(mostVisitedIPs);
    }

    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        HashMap<String, ArrayList<String>> dayToIPMap = la.iPsForDays();
        System.out.println(dayToIPMap);
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        HashMap<String, ArrayList<String>> dayToIPMap = la.iPsForDays();
        String dayWithMostVisits = la.dayWithMostIPVisits(dayToIPMap);
        System.out.println(dayWithMostVisits);
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("WebLogProgram/weblog2_log");
        HashMap<String, ArrayList<String>> dayToIPMap = la.iPsForDays();
        ArrayList<String> ipsWithMostDayVisits = la.iPsWithMostVisitsOnDay(dayToIPMap, "Sep 30");
        System.out.println(ipsWithMostDayVisits);
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        System.out.println("++++Testing LogEntry Class++++");
        t.testLogEntry();
        System.out.println("++++Testing LogAnalyzer Class++++");
        t.testLogAnalyzer();
        System.out.println("++++Testing number of Unique IPs method in LogAnalyzer++++");
        t.testUniqueIP();
        System.out.println("++++Testing PrintAllHigherThanNum method in LogAnalyzer++++");
        t.testPrintAllHigherThanNum();
        System.out.println("++++Testing UniqueIPVisitsOnDay method in LogAnalyzer++++");
        t.testUniqueIPVisitsOnDay();
        System.out.println("++++Testing CountUniqueIPsInRange method in LogAnalyzer++++");
        t.testCountUniqueIPsInRange();
        System.out.println("++++HashMap for CountVisitsPerIP++++");
        t.testCountVisitsPerIP();
        System.out.println("++++Max visits by a single IP address++++");
        t.testMostNumberVisitsByIP();
        System.out.println("++++ArrayList of IP addresses that all have the maximum number of visits to this website++++");
        t.testIPsMostVisits();
        System.out.println("++++HashMap of days from web logs to an ArrayList of IP addresses that occurred on that day++++");
        t.testIPsForDays();
        System.out.println("++++The day that has the most IP address visits++++");
        t.testDayWithMostIPVisits();
        System.out.println("++++An ArrayList of IP addresses that had the most accesses on the given day++++");
        t.testIPsWithMostVisitsOnDay();
    }
}
