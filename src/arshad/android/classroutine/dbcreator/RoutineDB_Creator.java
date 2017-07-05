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

package arshad.android.classroutine.dbcreator;

import arshad.android.classroutine.dbcreator.database.Database;
import arshad.android.classroutine.dbcreator.importXML.ObjectDatabase;
import arshad.android.classroutine.dbcreator.importXML.XMLParser;
import arshad.android.classroutine.dbcreator.skeleton.XMLskeleton;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

/**
 *
 * @author Arshad
 */
public class RoutineDB_Creator {
    public static String VERSION = "0.1";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("VERSION : " + VERSION + "\n");
        if(args.length < 1 || args.length > 3) {
            printHelp();
            return;
        }
        switch (args[0]) {
            case "-s":
                System.out.println("Printing skeleton file to " + args[1]);
                XMLskeleton.buildAndExport(args[1]);
                break;
            case "-db":
                try {
                    System.out.println("Exporting from " + args[1] + " file to " + args[2]);
                    XMLParser parser = new XMLParser(args[1]);
                    ObjectDatabase objDB = parser.getObjectDatabase();
                    Database db = new Database(args[2]);
                    db.exportSQLiteDatabase(objDB);
                    db.closeDatabase();
                } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
                    Logger.getLogger(RoutineDB_Creator.class.getName()).log(Level.SEVERE, null, ex);
                }   break;
            default:
                printHelp();
                break;
        }
    }
    
    /**
     * Print help and usages
     */
    private static void printHelp() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parameters :").append("\n").append("-s filename").append("\t\t::");
        sb.append("Prints a skeleton xml to the file").append("\n");
        sb.append("-db xmlfile dbfile").append("\t::").append("Creates SQLite database with the given xmlfile, xmlfile must have to be in current PATH");
        System.out.println(sb.toString());
    }
    
}
