// Copyright 2017 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.google.devtools.build.android.desugar;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.collect.ImmutableList;
import com.google.devtools.build.android.desugar.BytecodeTypeInference.FrameInfo;
import com.google.devtools.build.android.desugar.BytecodeTypeInference.InferredType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/** Test for {@link BytecodeTypeInference.FrameInfo} */
@RunWith(JUnit4.class)
public class FrameInfoTest {

  @Test
  public void testFieldsAreSetCorrectly() {
    {
      FrameInfo info = FrameInfo.create(ImmutableList.of(), ImmutableList.of());
      assertThat(info.locals()).isEmpty();
      assertThat(info.stack()).isEmpty();
    }
    {
      FrameInfo info =
          FrameInfo.create(ImmutableList.of(InferredType.INT), ImmutableList.of(InferredType.BYTE));
      assertThat(info.locals()).containsExactly(InferredType.INT).inOrder();
      assertThat(info.stack()).containsExactly(InferredType.BYTE).inOrder();
    }
    {
      FrameInfo info =
          FrameInfo.create(
              ImmutableList.of(InferredType.INT, InferredType.BYTE),
              ImmutableList.of(InferredType.BOOLEAN, InferredType.LONG));
      assertThat(info.locals()).containsExactly(InferredType.INT, InferredType.BYTE).inOrder();
      assertThat(info.stack()).containsExactly(InferredType.BOOLEAN, InferredType.LONG).inOrder();
    }
  }
}
