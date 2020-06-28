/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.sample;

import org.openjdk.jmh.annotations.*;
import com.sun.jna.Library;
import com.sun.jna.Native;

import jnr.ffi.LibraryLoader;
import jnr.ffi.annotations.IgnoreError;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
public class NativeBenchmark {

   public interface UsualNatives extends Library {
        UsualNatives INSTANCE = (UsualNatives) Native.load("UsualNatives", UsualNatives.class);
        void test();
    }

    public static interface NativesJNR {
        void test();
    }

    public static interface NativesJNR_IgnoreError {
        @IgnoreError
        void test();
    }

    Object sendToNative = new Object();

    NativeBenchmark instance;
    NativesJNR libJNR;
    NativesJNR_IgnoreError libJNR_IgnoreError;

    @Setup
    public void prepare() {
       instance = new NativeBenchmark();
       System.loadLibrary("JNINatives");
       libJNR = LibraryLoader.create(NativesJNR.class).load("UsualNatives");
       libJNR_IgnoreError = LibraryLoader.create(NativesJNR_IgnoreError.class).load("UsualNatives");
    }
  
    @TearDown
    public void check() {

    }


    public static void nativeUpcall() {
        // this method was intentionally left blank
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void javaUpcall() {
        // this method was intentionally left blank
    }

    // ---------------------

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void javaDowncallAndUpcall() {
        javaUpcall(); 
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void javaDowncall() {
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    @Fork(1)
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testNativeDowncall() {
        goNative();
    } 

    @Benchmark
    @BenchmarkMode({Mode.Throughput})
    @Fork(1)
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testNativeDowncallInstance() {
        instance.goNativeInstance();
    } 

    @Benchmark
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testJNADownCall() {
        UsualNatives.INSTANCE.test();
    }

    @Benchmark
    @Fork(1)
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testJavaDowncall() {
        javaDowncall();
    } 

    @Benchmark
    @Fork(1)
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testJavaDowncallAndUpcall() {
        javaDowncallAndUpcall();
    }

    @Benchmark
    @Fork(1)
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testNativeDowncallAndUpCall() {
        goNativeAndBack();
    }


    @Benchmark
    @Fork(value = 1)
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testJNRDownCall() {
        libJNR.test();
    }

    @Benchmark
    @Fork(1)
    @BenchmarkMode({Mode.Throughput})
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testJNRDownCallIgnoreException() {
        libJNR_IgnoreError.test();
    } 
   
    // ---------------------
  
    static native void goNative();

    public final native void goNativeInstance();

    static native void goNativeAndBack();

    static native void goNativeWithObject(Object object);

    static native void goNativeWithInt(int value);

}
