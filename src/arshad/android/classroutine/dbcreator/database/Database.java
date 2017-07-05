//    Copyright (C) 2017 MD. Ibrahim Khan
//
//    Project Name: 
//    Author: MD. Ibrahim Khan
//    Author's Email: ib.arshad777@gmail.com
//
//    Redistribution and use in source and binary forms, with or without modification,
//    are permitted provided that the following conditions are met:
//
//    1. Redistributions of source code must retain the above copyright notice, this
//       list of conditions and the following disclaimer.
//
//    2. Redistributions in binary form must reproduce the above copyright notice, this
//       list of conditions and the following disclaimer in the documentation and/or
//       other materials provided with the distribution.
//
//    3. Neither the name of the copyright holder nor the names of the contributors may
//       be used to endorse or promote products derived from this software without
//       specific prior written permission.
//
//    THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
//    ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
//    WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
//    IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
//    INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING
//    BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
//    DATA, OR PROFITS; OR BUSINESS INTERRUPTIONS) HOWEVER CAUSED AND ON ANY THEORY OF
//    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
//    OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
//    OF THE POSSIBILITY OF SUCH DAMAGE.

package arshad.android.classroutine.dbcreator.database;

import arshad.android.classroutine.dbcreator.importXML.ObjectDatabase;
import arshad.android.classroutine.dbcreator.model.ClassInfo;
import arshad.android.classroutine.dbcreator.model.Days;
import arshad.android.classroutine.dbcreator.model.Inputs;
import arshad.android.classroutine.dbcreator.model.TeacherInfo;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arshad
 */
public class Database {
    // Database specific String variables
    private String databaseLocation = "jdbc:sqlite:";
    //Database connectivity variables
    private Connection databaseConn;
    private Statement databaseStmt;
    private ResultSet databaseRs;

    // Load database driver
    static {
        try {
            Class.forName("org.sqlite.JDBC").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    // Constructor for database class
    public Database(String dbFile) {
        File file = new File(dbFile);
        if(file.exists()) {
            file.delete();
        }
        try {
            // Get connection from the database location
            databaseConn = DriverManager.getConnection(databaseLocation + dbFile);
            databaseStmt = (Statement) databaseConn.createStatement();
            databaseStmt.execute("CREATE TABLE \"saturday\" (" +
                    " `index` INTEGER," +
                    " `coursecode` TEXT," +
                    " `coursename` TEXT," +
                    " `room` TEXT," +
                    " `time` TEXT," +
                    " `courseteacher` TEXT" +
                    ");");
            databaseStmt.execute("CREATE TABLE \"sunday\" (" +
                    " `index` INTEGER," +
                    " `coursecode` TEXT," +
                    " `coursename` TEXT," +
                    " `room` TEXT," +
                    " `time` TEXT," +
                    " `courseteacher` TEXT" +
                    ");");
            databaseStmt.execute("CREATE TABLE \"monday\" (" +
                    " `index` INTEGER," +
                    " `coursecode` TEXT," +
                    " `coursename` TEXT," +
                    " `room` TEXT," +
                    " `time` TEXT," +
                    " `courseteacher` TEXT" +
                    ");");
            databaseStmt.execute("CREATE TABLE \"tuesday\" (" +
                    " `index` INTEGER," +
                    " `coursecode` TEXT," +
                    " `coursename` TEXT," +
                    " `room` TEXT," +
                    " `time` TEXT," +
                    " `courseteacher` TEXT" +
                    ");");
            databaseStmt.execute("CREATE TABLE \"wednesday\" (" +
                    " `index` INTEGER," +
                    " `coursecode` TEXT," +
                    " `coursename` TEXT," +
                    " `room` TEXT," +
                    " `time` TEXT," +
                    " `courseteacher` TEXT" +
                    ");");
            databaseStmt.execute("CREATE TABLE \"thursday\" (" +
                    " `index` INTEGER," +
                    " `coursecode` TEXT," +
                    " `coursename` TEXT," +
                    " `room` TEXT," +
                    " `time` TEXT," +
                    " `courseteacher` TEXT" +
                    ");");
            databaseStmt.execute("CREATE TABLE \"friday\" (" +
                    " `index` INTEGER," +
                    " `coursecode` TEXT," +
                    " `coursename` TEXT," +
                    " `room` TEXT," +
                    " `time` TEXT," +
                    " `courseteacher` TEXT" +
                    ");");
            databaseStmt.execute("CREATE TABLE `version` (" +
                    " `versioninfo`	INTEGER" +
                    ");");
            databaseStmt.execute("CREATE TABLE `extrainfo` (" +
                    " `name` TEXT," +
                    " `department` TEXT," +
                    " `initial` TEXT," +
                    " `mobile` TEXT," +
                    " `email` TEXT" +
                    ");");
            databaseStmt.execute("CREATE TABLE `deadlines` (" +
                    " `index` INTEGER," +
                    " `topic` TEXT," +
                    " `info1` TEXT," +
                    " `info2` TEXT," +
                    " `info3` TEXT," +
                    " `info4` TEXT" +
                    ");");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Close database connection and statement connection.
    public void closeDatabase() {
        try {
            this.databaseStmt.close();
            this.databaseConn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void exportSQLiteDatabase(ObjectDatabase objDB) {
        try {
            databaseStmt.execute("INSERT INTO `version` (versioninfo) VALUES (" + objDB.getCurrentVersion() + ");");
            
            ArrayList<ClassInfo> classInfo;
            classInfo = objDB.getDays().getClassList(Days.DAY_FRIDAY);
            for(ClassInfo tmp : classInfo) {
                databaseStmt.execute("INSERT INTO `friday` (`index`,`coursecode`,`coursename`,`room`,`time`,`courseteacher`) VALUES (" + tmp.getIndex() +  ",'" + tmp.getCoursecode() + "','" + tmp.getCoursename() + "','" + tmp.getRoom() + "','" + tmp.getTime() + "','" + tmp.getCourseteacher() + "');");
            }
            classInfo = objDB.getDays().getClassList(Days.DAY_MONDAY);
            for(ClassInfo tmp : classInfo) {
                databaseStmt.execute("INSERT INTO `monday` (`index`,`coursecode`,`coursename`,`room`,`time`,`courseteacher`) VALUES (" + tmp.getIndex() +  ",'" + tmp.getCoursecode() + "','" + tmp.getCoursename() + "','" + tmp.getRoom() + "','" + tmp.getTime() + "','" + tmp.getCourseteacher() + "');");
            }
            classInfo = objDB.getDays().getClassList(Days.DAY_SATURDAY);
            for(ClassInfo tmp : classInfo) {
                databaseStmt.execute("INSERT INTO `saturday` (`index`,`coursecode`,`coursename`,`room`,`time`,`courseteacher`) VALUES (" + tmp.getIndex() +  ",'" + tmp.getCoursecode() + "','" + tmp.getCoursename() + "','" + tmp.getRoom() + "','" + tmp.getTime() + "','" + tmp.getCourseteacher() + "');");
            }
            classInfo = objDB.getDays().getClassList(Days.DAY_SUNDAY);
            for(ClassInfo tmp : classInfo) {
                databaseStmt.execute("INSERT INTO `sunday` (`index`,`coursecode`,`coursename`,`room`,`time`,`courseteacher`) VALUES (" + tmp.getIndex() +  ",'" + tmp.getCoursecode() + "','" + tmp.getCoursename() + "','" + tmp.getRoom() + "','" + tmp.getTime() + "','" + tmp.getCourseteacher() + "');");
            }
            classInfo = objDB.getDays().getClassList(Days.DAY_THURSDAY);
            for(ClassInfo tmp : classInfo) {
                databaseStmt.execute("INSERT INTO `thursday` (`index`,`coursecode`,`coursename`,`room`,`time`,`courseteacher`) VALUES (" + tmp.getIndex() +  ",'" + tmp.getCoursecode() + "','" + tmp.getCoursename() + "','" + tmp.getRoom() + "','" + tmp.getTime() + "','" + tmp.getCourseteacher() + "');");
            }
            classInfo = objDB.getDays().getClassList(Days.DAY_TUESDAY);
            for(ClassInfo tmp : classInfo) {
                databaseStmt.execute("INSERT INTO `tuesday` (`index`,`coursecode`,`coursename`,`room`,`time`,`courseteacher`) VALUES (" + tmp.getIndex() +  ",'" + tmp.getCoursecode() + "','" + tmp.getCoursename() + "','" + tmp.getRoom() + "','" + tmp.getTime() + "','" + tmp.getCourseteacher() + "');");
            }
            classInfo = objDB.getDays().getClassList(Days.DAY_WEDNESDAY);
            for(ClassInfo tmp : classInfo) {
                databaseStmt.execute("INSERT INTO `wednesday` (`index`,`coursecode`,`coursename`,`room`,`time`,`courseteacher`) VALUES (" + tmp.getIndex() +  ",'" + tmp.getCoursecode() + "','" + tmp.getCoursename() + "','" + tmp.getRoom() + "','" + tmp.getTime() + "','" + tmp.getCourseteacher() + "');");
            }
            
            ArrayList<TeacherInfo> teachList;
            teachList = objDB.getExtrainfo().getTeacherInfoList();
            for(TeacherInfo tmp : teachList) {
                databaseStmt.execute("INSERT INTO `extrainfo` (`name`,`department`,`initial`,`mobile`,`email`) VALUES ('" + tmp.getName() + "','" + tmp.getDepartment() + "','" + tmp.getInitial() + "','" + tmp.getMobile() + "','" + tmp.getEmail() + "');");
            }
            
            ArrayList<Inputs> deadlines;
            deadlines = objDB.getDeadlines().getDeadlinesList();
            for(Inputs tmp : deadlines) {
                databaseStmt.execute("INSERT INTO `deadlines` (`index`,`topic`,`info1`,`info2`,`info3`,`info4`) VALUES (" + tmp.getIndex() +",'" + tmp.getTopic() +"','" + tmp.getInfo1() +"','" + tmp.getInfo2() +"','" + tmp.getInfo3() +"','" + tmp.getInfo4() +"');");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
