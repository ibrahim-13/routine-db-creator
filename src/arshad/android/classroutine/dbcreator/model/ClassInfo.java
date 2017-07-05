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

package arshad.android.classroutine.dbcreator.model;

/**
 *
 * @author Arshad
 */
public class ClassInfo {
    private final String index;
    private final String coursecode;
    private final String coursename;
    private final String room;
    private final String time;
    private final String courseteacher;

    public ClassInfo(String index, String coursecode, String coursename, String room, String time, String courseteacher) {
        this.index = index;
        this.coursecode = coursecode;
        this.coursename = coursename;
        this.room = room;
        this.time = time;
        this.courseteacher = courseteacher;
    }

    public String getIndex() {
        return index;
    }

    public String getCoursecode() {
        return coursecode;
    }

    public String getCoursename() {
        return coursename;
    }

    public String getRoom() {
        return room;
    }

    public String getTime() {
        return time;
    }

    public String getCourseteacher() {
        return courseteacher;
    }
}