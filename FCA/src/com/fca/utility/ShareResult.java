/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fca.utility;

import com.fca.operations.Relation;

/**
 *
 * @author susmi
 */
public class ShareResult {
      public  static Relation result;

        public static void setResult(Relation result) {
            ShareResult.result = result;
        }

        public static Relation getResult() {
            return result;
        }
}
