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

import jdk.incubator.foreign.*;
import java.lang.invoke.*;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

import org.sample.RuntimeHelper;
import org.sample.panamatest_h;

import static org.sample.panamatest_h.*;

@State(Scope.Thread)
public class NativeBenchmark {

    NativeBenchmark instance;
    static final MethodHandle mh = panamatest_h.test$MH();

    static final MethodHandle downcallMH = RuntimeHelper.downcallHandle(
            new LibraryLookup[]{LibraryLookup.ofLibrary("panamatest")}, "test",
            "()V",
            FunctionDescriptor.ofVoid(),
            false);
   
    @Setup
    public void prepare() {
       instance = new NativeBenchmark();
       System.loadLibrary("JNINatives");
    }
  
    @TearDown
    public void check() {

    }

    @Benchmark
    @Fork(value = 1, jvmArgsAppend="-Dforeign.restricted=permit")
    @BenchmarkMode({Mode.Throughput})
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testNativeDowncallInstance11() {
        instance.goNativeInstance();
    }

    @Benchmark
    @Fork(value = 1, jvmArgsAppend="-Dforeign.restricted=permit")
    @BenchmarkMode({Mode.Throughput})
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testPanamaMh() throws Throwable {
        mh.invokeExact();
    }

    @Benchmark
    @Fork(value = 1, jvmArgsAppend="-Dforeign.restricted=permit")
    @BenchmarkMode({Mode.Throughput})
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void testPanamaDowncall() throws Throwable {
        downcallMH.invokeExact();
    }
  
    public final native void goNativeInstance();

    public static void nativeUpcall() {
        // this method was intentionally left blank
    }

}
