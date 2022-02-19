#  Copyright 2020, 2021, Ludovic Henry
#
#  Permission is hereby granted, free of charge, to any person obtaining a copy
#  of this software and associated documentation files (the \"Software\"), to deal
#  in the Software without restriction, including without limitation the rights
#  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
#  copies of the Software, and to permit persons to whom the Software is
#  furnished to do so, subject to the following conditions:
#
#  The above copyright notice and this permission notice shall be included in
#  all copies or substantial portions of the Software.
#
#  THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
#  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
#  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
#  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
#  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
#  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
#  SOFTWARE.
#
#  Please contact git@ludovic.dev or visit ludovic.dev if you need additional
#  information or have any questions.

import sys

class JBooleanR:
  def __init__(self):
    self.native_type = "int"
    self.java_type = "jboolean"
class JIntR:
  def __init__(self):
    self.native_type = "int"
    self.java_type = "jint"
class JFloatR:
  def __init__(self):
    self.native_type = "float"
    self.java_type = "jfloat"
class JDoubleR:
  def __init__(self):
    self.native_type = "double"
    self.java_type = "jdouble"

class JBoolean:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "int *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jboolean {name}"]]
    self.native_argument = "&__n{name}".format(name=name)
    self.native_local = "int __n{name} __attribute__((aligned(8)));".format(name=name)
    self.prolog = "__n{name} = {name};".format(name=name)
    self.epilog = ""
class JInt:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "int *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jint {name}"]]
    self.native_argument = "&__n{name}".format(name=name)
    self.native_local = "int __n{name} __attribute__((aligned(8)));".format(name=name)
    self.prolog = "__n{name} = {name};".format(name=name)
    self.epilog = ""
class JLong:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "long *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jlong {name}"]]
    self.native_argument = "&__n{name}".format(name=name)
    self.native_local = "long __n{name} __attribute__((aligned(8)));".format(name=name)
    self.prolog = "__n{name} = {name};".format(name=name)
    self.epilog = ""
class JFloat:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "float *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jfloat {name}"]]
    self.native_argument = "&__n{name}".format(name=name)
    self.native_local = "float __n{name} __attribute__((aligned(8)));".format(name=name)
    self.prolog = "__n{name} = {name};".format(name=name)
    self.epilog = ""
class JDouble:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "double *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jdouble {name}"]]
    self.native_argument = "&__n{name}".format(name=name)
    self.native_local = "double __n{name} __attribute__((aligned(8)));".format(name=name)
    self.prolog = "__n{name} = {name};".format(name=name)
    self.epilog = ""
class JString:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "const char *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jstring {name}"]]
    self.native_argument = "__n{name}".format(name=name)
    self.native_local = "const char *__n{name} = NULL;".format(name=name)
    self.prolog = "if (!(__n{name} = (*env)->GetStringUTFChars(env, {name}, NULL))) {{ __failed = TRUE; goto done; }}".format(name=name)
    self.epilog = "if (__n{name}) (*env)->ReleaseStringUTFChars(env, {name}, __n{name});".format(name=name)
class JObject:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "const char *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jstring {name}"]]
    # self.native_argument = "__n{name}".format(name=name)
    # self.native_local = "const char *__n{name} = NULL;".format(name=name)
    # self.prolog = "if (!(__n{name} = (*env)->GetStringUTFChars(env, {name}, NULL))) {{ __failed = TRUE; goto done; }}".format(name=name)
    # self.epilog = "if (__n{name}) (*env)->ReleaseStringUTFChars(env, {name}, __n{name});".format(name=name)

class JBooleanW:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "int *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jobject {name}"]]
    self.native_argument = "&__n{name}".format(name=name)
    self.native_local = "int __n{name} = 0;".format(name=name)
    self.prolog = "__n{name} = (*env)->GetBooleanField(env, {name}, booleanW_val_fieldID);".format(name=name)
    self.epilog = "if (!__failed) (*env)->SetBooleanField(env, {name}, booleanW_val_fieldID, __n{name});".format(name=name)
class JIntW:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "int *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jobject {name}"]]
    self.native_argument = "&__n{name}".format(name=name)
    self.native_local = "int __n{name} = 0;".format(name=name)
    self.prolog = "__n{name} = (*env)->GetIntField(env, {name}, intW_val_fieldID);".format(name=name)
    self.epilog = "if (!__failed) (*env)->SetIntField(env, {name}, intW_val_fieldID, __n{name});".format(name=name)
class JFloatW:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "float *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jobject {name}"]]
    self.native_argument = "&__n{name}".format(name=name)
    self.native_local = "float __n{name} = 0;".format(name=name)
    self.prolog = "__n{name} = (*env)->GetFloatField(env, {name}, floatW_val_fieldID);".format(name=name)
    self.epilog = "if (!__failed) (*env)->SetFloatField(env, {name}, floatW_val_fieldID, __n{name});".format(name=name)
class JDoubleW:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "double *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jobject {name}"]]
    self.native_argument = "&__n{name}".format(name=name)
    self.native_local = "double __n{name} = 0;".format(name=name)
    self.prolog = "__n{name} = (*env)->GetDoubleField(env, {name}, doubleW_val_fieldID);".format(name=name)
    self.epilog = "if (!__failed) (*env)->SetDoubleField(env, {name}, doubleW_val_fieldID, __n{name});".format(name=name)
class JStringW:
  def __init__(self, name):
    self.idx = 0
    self.name = name
    self.native_type_and_name = "char *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jobject {name}"]]
    self.native_argument = "__n{name}".format(name=name)
    self.native_local = "char *__n{name} = NULL; jstring __j{name} = NULL;".format(name=name)
    self.prolog = "__j{name} = (jstring)(*env)->GetObjectField(env, {name}, StringW_val_fieldID); if (!(__n{name} = (char*)(*env)->GetStringUTFChars(env, {name}, NULL))) {{ __failed = TRUE; goto done; }}".format(name=name)
    self.epilog = "if (__n{name}) {{ (*env)->ReleaseStringUTFChars(env, __j{name}, (const char*)__n{name}); if (!__failed) (*env)->SetObjectField(env, {name}, StringW_val_fieldID, __j{name}); }}".format(name=name)

class JBooleanArray:
  def __init__(self, name):
    self.idx = 1
    self.name = name
    self.native_type_and_name = "int *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jbooleanArray {name}", "jint offset{name}"]]
    self.native_argument = "__n{name} + offset{name}".format(name=name)
    self.native_local = "int *__n{name} = NULL; jboolean *__j{name} = NULL;".format(name=name)
    self.prolog = """if (!(__j{name} = (*env)->GetPrimitiveArrayCritical(env, {name}, NULL))) {{ __failed = TRUE; goto done; }}
  do {{
    int __length = (*env)->GetArrayLength(env, {name});
    if (__length <= 0) {{ __failed = TRUE; goto done; }}
    if (!(__n{name} = malloc(sizeof(int) * __length))) {{ __failed = TRUE; goto done; }}
    for (int i = 0; i < __length; i++) {{ __n{name}[i] = __j{name}[i]; }}
  }} while(0);""".format(name=name)
    self.epilog = "if (__n{name}) {{ free(__n{name}); }} if (__j{name}) (*env)->ReleasePrimitiveArrayCritical(env, {name}, __n{name}, JNI_ABORT);".format(name=name)
class JIntArray:
  def __init__(self, name, mode = "0"):
    self.idx = 1
    self.name = name
    self.native_type_and_name = "int *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jintArray {name}", "jint offset{name}"]]
    self.native_argument = "__n{name} + offset{name}".format(name=name)
    self.native_local = "int *__n{name} = NULL;".format(name=name)
    self.prolog = "if (!(__n{name} = (*env)->GetPrimitiveArrayCritical(env, {name}, NULL))) {{ __failed = TRUE; goto done; }}".format(name=name)
    self.epilog = "if (__n{name}) (*env)->ReleasePrimitiveArrayCritical(env, {name}, __n{name}, {mode});".format(name=name, mode=("JNI_ABORT" if mode == "JNI_ABORT" else ("__failed ? JNI_ABORT : %s" % mode)))
class JFloatArray:
  def __init__(self, name, mode = "0"):
    self.idx = 1
    self.name = name
    self.native_type_and_name = "float *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jfloatArray {name}", "jint offset{name}"]]
    self.native_argument = "__n{name} + offset{name}".format(name=name)
    self.native_local = "float *__n{name} = NULL;".format(name=name)
    self.prolog = "if (!(__n{name} = (*env)->GetPrimitiveArrayCritical(env, {name}, NULL))) {{ __failed = TRUE; goto done; }}".format(name=name)
    self.epilog = "if (__n{name}) (*env)->ReleasePrimitiveArrayCritical(env, {name}, __n{name}, {mode});".format(name=name, mode=("JNI_ABORT" if mode == "JNI_ABORT" else ("__failed ? JNI_ABORT : %s" % mode)))
class JDoubleArray:
  def __init__(self, name, mode = "0"):
    self.idx = 1
    self.name = name
    self.native_type_and_name = "double *{name}".format(name=name)
    self.java_type_and_name = [a.format(name=name) for a in ["jdoubleArray {name}", "jint offset{name}"]]
    self.native_argument = "__n{name} + offset{name}".format(name=name)
    self.native_local = "double *__n{name} = NULL;".format(name=name)
    self.prolog = "if (!(__n{name} = (*env)->GetPrimitiveArrayCritical(env, {name}, NULL))) {{ __failed = TRUE; goto done; }}".format(name=name)
    self.epilog = "if (__n{name}) (*env)->ReleasePrimitiveArrayCritical(env, {name}, __n{name}, {mode});".format(name=name, mode=("JNI_ABORT" if mode == "JNI_ABORT" else ("__failed ? JNI_ABORT : %s" % mode)))

class RoutineR:
  def __init__(self, ret, name, *args):
    self.ret = ret
    self.name = name
    self.args = args

  def render(self, pkg):
    # Print native function signature
    print("static {ret} (*{name}_)({args});".format(ret=self.ret.native_type, name=self.name, args=", ".join([arg.native_type_and_name for arg in self.args])))
    print()
    # Print JNI function implementation
    print("jboolean Java_dev_ludovic_netlib_{pkg}_JNI{pkgupper}_has_{name}(UNUSED JNIEnv *env, UNUSED jobject obj) {{".format(pkg=pkg, pkgupper=pkg.upper(), name=self.name))
    print("  return {name}_ != NULL;".format(name=self.name))
    print("}")
    print()
    print("{ret} Java_dev_ludovic_netlib_{pkg}_JNI{pkgupper}_{name}K(JNIEnv *env, UNUSED jobject obj{args}) {{".format(ret=self.ret.java_type, pkg=pkg, pkgupper=pkg.upper(), name=self.name, args="".join([", " + a for arg in self.args for a in arg.java_type_and_name]))) 
    print("  if (!{name}_) (*env)->ThrowNew(env, (*env)->FindClass(env, \"java/lang/UnsupportedOperationException\"), \"symbol isn't available in native library\");".format(name=self.name))
    print("  {rettype} __ret = 0;".format(rettype=self.ret.java_type))
    print("  jboolean __failed = FALSE;")
    if any(len(arg.native_local) > 0 for arg in sorted(self.args, key=lambda a: a.idx)):
      print("\n".join(["  " + a for a in [arg.native_local for arg in sorted(self.args, key=lambda a: a.idx)] if len(a) > 0]))
    if any(len(arg.prolog) > 0 for arg in sorted(self.args, key=lambda a: a.idx)):
      print("\n".join(["  " + a for a in [arg.prolog for arg in sorted(self.args, key=lambda a: a.idx)] if len(a) > 0]))
    print("  __ret = {name}_({args});".format(name=self.name, args=", ".join([arg.native_argument for arg in self.args])))
    print("done:")
    if any(len(arg.epilog) > 0 for arg in sorted(self.args, key=lambda a: a.idx)):
      print("\n".join(["  " + a for a in [arg.epilog for arg in sorted(self.args, key=lambda a: a.idx)] if len(a) > 0][::-1]))
    print("  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, \"java/lang/OutOfMemoryError\"), \"Failed to copy from heap to native memory\");")
    print("  return __ret;")
    print("}")
    print()

  def render_load_symbol(self):
    print("  LOAD_SYMBOL({name}_);".format(name=self.name))

class RoutineR_NI:
  def __init__(self, ret, name, *args):
    self.ret = ret
    self.name = name
    self.args = args

  def render(self, pkg):
    # Print native function signature
    print("// static {ret} (*{name}_)({args});".format(ret=self.ret.native_type, name=self.name, args=", ".join([arg.native_type_and_name for arg in self.args])))
    print()
    # Print JNI function implementation
    print("jboolean Java_dev_ludovic_netlib_{pkg}_JNI{pkgupper}_has_{name}(UNUSED JNIEnv *env, UNUSED jobject obj) {{".format(pkg=pkg, pkgupper=pkg.upper(), name=self.name))
    print("  return FALSE;")
    print("}")
    print()
    print("{ret} Java_dev_ludovic_netlib_{pkg}_JNI{pkgupper}_{name}K(JNIEnv *env, UNUSED jobject obj{args}) {{".format(ret=self.ret.java_type, pkg=pkg, pkgupper=pkg.upper(), name=self.name, args="".join([", UNUSED " + arg.java_type_and_name for arg in self.args])))
    print("  (*env)->ThrowNew(env, (*env)->FindClass(env, \"java/lang/UnsupportedOperationException\"), \"not implemented\");")
    print("  return 0;")
    print("}")
    print()

  def render_load_symbol(self):
    print("  // LOAD_SYMBOL({name}_);".format(name=self.name))

class Routine:
  def __init__(self, name, *args):
    self.name = name
    self.args = args

  def render(self, pkg):
    # Print native function signature
    print("static void (*{name}_)({args});".format(name=self.name, args=", ".join([arg.native_type_and_name for arg in self.args])))
    print()
    # Print JNI function implementation
    print("jboolean Java_dev_ludovic_netlib_{pkg}_JNI{pkgupper}_has_{name}(UNUSED JNIEnv *env, UNUSED jobject obj) {{".format(pkg=pkg, pkgupper=pkg.upper(), name=self.name))
    print("  return {name}_ != NULL;".format(name=self.name))
    print("}")
    print()
    print("void Java_dev_ludovic_netlib_{pkg}_JNI{pkgupper}_{name}K(JNIEnv *env, UNUSED jobject obj{args}) {{".format(pkg=pkg, pkgupper=pkg.upper(), name=self.name, args="".join([", " + a for arg in self.args for a in arg.java_type_and_name])))
    print("  if (!{name}_) (*env)->ThrowNew(env, (*env)->FindClass(env, \"java/lang/UnsupportedOperationException\"), \"symbol isn't available in native library\");".format(name=self.name))
    print("  jboolean __failed = FALSE;")
    if any(len(arg.native_local) > 0 for arg in sorted(self.args, key=lambda a: a.idx)):
      print("\n".join(["  " + a for a in [arg.native_local for arg in sorted(self.args, key=lambda a: a.idx)] if len(a) > 0]))
    if any(len(arg.prolog) > 0 for arg in sorted(self.args, key=lambda a: a.idx)):
      print("\n".join(["  " + a for a in [arg.prolog for arg in sorted(self.args, key=lambda a: a.idx)] if len(a) > 0]))
    print("  {name}_({args});".format(name=self.name, args=", ".join([arg.native_argument for arg in self.args])))
    print("done:")
    if any(len(arg.epilog) > 0 for arg in sorted(self.args, key=lambda a: a.idx)):
      print("\n".join(["  " + a for a in [arg.epilog for arg in sorted(self.args, key=lambda a: a.idx)] if len(a) > 0][::-1]))
    print("  if (__failed) (*env)->ThrowNew(env, (*env)->FindClass(env, \"java/lang/OutOfMemoryError\"), \"Failed to copy from heap to native memory\");")
    print("}")
    print()

  def render_load_symbol(self):
    print("  LOAD_SYMBOL({name}_);".format(name=self.name))

class Routine_NI:
  def __init__(self, name, *args):
    self.name = name
    self.args = args

  def render(self, pkg):
    # Print native function signature
    print("// static void (*{name}_)({args});".format(name=self.name, args=", ".join([arg.native_type_and_name for arg in self.args])))
    print()
    # Print JNI function implementation
    print("jboolean Java_dev_ludovic_netlib_{pkg}_JNI{pkgupper}_has_{name}(UNUSED JNIEnv *env, UNUSED jobject obj) {{".format(pkg=pkg, pkgupper=pkg.upper(), name=self.name))
    print("  return FALSE;")
    print("}")
    print()
    print("void Java_dev_ludovic_netlib_{pkg}_JNI{pkgupper}_{name}K(JNIEnv *env, UNUSED jobject obj{args}) {{".format(pkg=pkg, pkgupper=pkg.upper(), name=self.name, args="".join([", UNUSED " + a for arg in self.args for a in arg.java_type_and_name])))
    print("  (*env)->ThrowNew(env, (*env)->FindClass(env, \"java/lang/UnsupportedOperationException\"), \"not implemented\");")
    print("}")
    print()

  def render_load_symbol(self):
    print("  // LOAD_SYMBOL({name}_);".format(name=self.name))

class Library:
  def __init__(self, pkg, libname, *routines):
    # Print copyright header
    print("/*")
    print(" * Copyright 2020, 2021, Ludovic Henry")
    print(" *")
    print(" * Permission is hereby granted, free of charge, to any person obtaining a copy")
    print(" * of this software and associated documentation files (the \"Software\"), to deal")
    print(" * in the Software without restriction, including without limitation the rights")
    print(" * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell")
    print(" * copies of the Software, and to permit persons to whom the Software is")
    print(" * furnished to do so, subject to the following conditions:")
    print(" *")
    print(" * The above copyright notice and this permission notice shall be included in")
    print(" * all copies or substantial portions of the Software.")
    print(" *")
    print(" * THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR")
    print(" * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,")
    print(" * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE")
    print(" * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER")
    print(" * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,")
    print(" * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE")
    print(" * SOFTWARE.")
    print(" *")
    print(" * Please contact git@ludovic.dev or visit ludovic.dev if you need additional")
    print(" * information or have any questions.")
    print(" */")
    print()
    # Print includes
    print("#include <stdlib.h>")
    print("#include <string.h>")
    print("#include <dlfcn.h>")
    print()
    print("#include \"dev_ludovic_netlib_{pkg}_JNI{pkgupper}.h\"".format(pkg=pkg, pkgupper=pkg.upper()))
    print()
    # Print defines
    print("#define UNUSED __attribute__((unused))")
    print()
    print("#define TRUE 1")
    print("#define FALSE 0")
    print()
    # Print static fields
    print("static jfieldID booleanW_val_fieldID;")
    print("static jfieldID intW_val_fieldID;")
    print("static jfieldID floatW_val_fieldID;")
    print("static jfieldID doubleW_val_fieldID;")
    print("static jfieldID StringW_val_fieldID;")
    print()
    # Print routines bodies
    for routine in routines:
      routine.render(pkg)
    # Print helper functions
    print("jboolean get_system_property(JNIEnv *env, jstring key, jstring def, jstring *res) {")
    print("  jclass System_class = (*env)->FindClass(env, \"java/lang/System\");")
    print("  if (!System_class) {")
    print("    return FALSE;")
    print("  }")
    print("  jmethodID System_getProperty_methodID = (*env)->GetStaticMethodID(env, System_class, \"getProperty\", \"(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;\");")
    print("  if (!System_getProperty_methodID) {")
    print("    return FALSE;")
    print("  }")
    print("  *res = (jstring)(*env)->CallStaticObjectMethod(env, System_class, System_getProperty_methodID, key, def);")
    print("  return TRUE;")
    print("}")
    print()
    # Print symbols loading
    print("jboolean load_symbols(void) {")
    print("#define LOAD_SYMBOL(name) \\")
    print("  name = dlsym(NULL, #name);")
    print("")
    for routine in routines:
      routine.render_load_symbol()
    print("")
    print("#undef LOAD_SYMBOL")
    print(" return TRUE;")
    print("}")
    print()
    # Print JNI entry functions
    print("static void *libhandle;")
    print()
    print("jint JNI_OnLoad(JavaVM *vm, UNUSED void *reserved) {")
    print("  JNIEnv *env;")
    print("  if ((*vm)->GetEnv(vm, (void**)&env, JNI_VERSION_1_6) != JNI_OK) {")
    print("    return -1;")
    print("  }")
    print("")
    print("  jclass booleanW_class = (*env)->FindClass(env, \"org/netlib/util/booleanW\");")
    print("  if (!booleanW_class) {")
    print("    return -1;")
    print("  }")
    print("  booleanW_val_fieldID = (*env)->GetFieldID(env, booleanW_class, \"val\", \"Z\");")
    print("  if (!booleanW_val_fieldID) {")
    print("    return -1;")
    print("  }")
    print("")
    print("  jclass intW_class = (*env)->FindClass(env, \"org/netlib/util/intW\");")
    print("  if (!intW_class) {")
    print("    return -1;")
    print("  }")
    print("  intW_val_fieldID = (*env)->GetFieldID(env, intW_class, \"val\", \"I\");")
    print("  if (!intW_val_fieldID) {")
    print("    return -1;")
    print("  }")
    print("")
    print("  jclass floatW_class = (*env)->FindClass(env, \"org/netlib/util/floatW\");")
    print("  if (!floatW_class) {")
    print("    return -1;")
    print("  }")
    print("  floatW_val_fieldID = (*env)->GetFieldID(env, floatW_class, \"val\", \"F\");")
    print("  if (!floatW_val_fieldID) {")
    print("    return -1;")
    print("  }")
    print("")
    print("  jclass doubleW_class = (*env)->FindClass(env, \"org/netlib/util/doubleW\");")
    print("  if (!doubleW_class) {")
    print("    return -1;")
    print("  }")
    print("  doubleW_val_fieldID = (*env)->GetFieldID(env, doubleW_class, \"val\", \"D\");")
    print("  if (!doubleW_val_fieldID) {")
    print("    return -1;")
    print("  }")
    print("")
    print("  jclass StringW_class = (*env)->FindClass(env, \"org/netlib/util/StringW\");")
    print("  if (!StringW_class) {")
    print("    return -1;")
    print("  }")
    print("  StringW_val_fieldID = (*env)->GetFieldID(env, StringW_class, \"val\", \"Ljava/lang/String;\");")
    print("  if (!StringW_val_fieldID) {")
    print("    return -1;")
    print("  }")
    print("")
    print("  jstring property_nativeLibPath;")
    print("  if (!get_system_property(env, (*env)->NewStringUTF(env, \"dev.ludovic.netlib.{pkg}.nativeLibPath\"), NULL, &property_nativeLibPath)) {{".format(pkg=pkg))
    print("    return -1;")
    print("  }")
    print("  jstring property_nativeLib;")
    print("  if (!get_system_property(env, (*env)->NewStringUTF(env, \"dev.ludovic.netlib.{pkg}.nativeLib\"), (*env)->NewStringUTF(env, \"{libname}\"), &property_nativeLib)) {{".format(pkg=pkg, libname=libname))
    print("    return -1;")
    print("  }")
    print("")
    print("  char name[1024];")
    print("  if (property_nativeLibPath) {")
    print("    const char *utf = (*env)->GetStringUTFChars(env, property_nativeLibPath, NULL);")
    print("    snprintf(name, sizeof(name), \"%s\", utf);")
    print("    (*env)->ReleaseStringUTFChars(env, property_nativeLibPath, utf);")
    print("  } else if (property_nativeLib) {")
    print("    const char *utf = (*env)->GetStringUTFChars(env, property_nativeLib, NULL);")
    print("    snprintf(name, sizeof(name), \"%s\", utf);")
    print("    (*env)->ReleaseStringUTFChars(env, property_nativeLib, utf);")
    print("  } else {")
    print("    /* either property_nativeLibPath or property_nativeLib should always be non-NULL */")
    print("    return -1;")
    print("  }")
    print("")
    print("  libhandle = dlopen(name, RTLD_LAZY | RTLD_GLOBAL);")
    print("  if (!libhandle) {")
    print("    return -1;")
    print("  }")
    print("")
    print("  if (!load_symbols()) {")
    print("    return -1;")
    print("  }")
    print("")
    print("  return JNI_VERSION_1_6;")
    print("}")
    print()
    print("void JNI_OnUnload(UNUSED JavaVM *vm, UNUSED void *reserved) {")
    print("  dlclose(libhandle);")
    print("}")


# Copy from Java

# $> []
# $< Array

# $> (, (int|float|double)Array [a-zA-Z0-9]+), int offset[a-zA-Z0-9]+
# $< $1

# $> ((boolean|int|float|double|String|java.lang.Object|org.netlib.util.(boolean|int|float|double|String)W)(Array)?) ([a-z0-9]+)
# $< $1("$5")

if sys.argv[1] == "blas":
  Library("blas", "libblas.so.3",
    RoutineR  (JDoubleR(), "dasum", JInt("n"), JDoubleArray("x", "JNI_ABORT"), JInt("incx")),
    RoutineR  (JFloatR(),  "sasum", JInt("n"), JFloatArray("x", "JNI_ABORT"), JInt("incx")),
    Routine   (            "daxpy", JInt("n"), JDouble("alpha"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDoubleArray("y"), JInt("incy")),
    Routine   (            "saxpy", JInt("n"), JFloat("alpha"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloatArray("y"), JInt("incy")),
    Routine   (            "dcopy", JInt("n"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDoubleArray("y"), JInt("incy")),
    Routine   (            "scopy", JInt("n"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloatArray("y"), JInt("incy")),
    RoutineR  (JDoubleR(), "ddot", JInt("n"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDoubleArray("y", "JNI_ABORT"), JInt("incy")),
    RoutineR  (JFloatR(),  "sdot", JInt("n"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloatArray("y", "JNI_ABORT"), JInt("incy")),
    RoutineR  (JFloatR(),  "sdsdot", JInt("n"), JFloat("sb"), JFloatArray("sx", "JNI_ABORT"), JInt("incsx"), JFloatArray("sy", "JNI_ABORT"), JInt("incsy")),
    Routine   (            "dgbmv", JString("trans"), JInt("m"), JInt("n"), JInt("kl"), JInt("ku"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDouble("beta"), JDoubleArray("y"), JInt("incy")),
    Routine   (            "sgbmv", JString("trans"), JInt("m"), JInt("n"), JInt("kl"), JInt("ku"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloat("beta"), JFloatArray("y"), JInt("incy")),
    Routine   (            "dgemm", JString("transa"), JString("transb"), JInt("m"), JInt("n"), JInt("k"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("b", "JNI_ABORT"), JInt("ldb"), JDouble("beta"), JDoubleArray("c"), JInt("ldc")),
    Routine   (            "sgemm", JString("transa"), JString("transb"), JInt("m"), JInt("n"), JInt("k"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("b", "JNI_ABORT"), JInt("ldb"), JFloat("beta"), JFloatArray("c"), JInt("ldc")),
    Routine   (            "dgemv", JString("trans"), JInt("m"), JInt("n"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDouble("beta"), JDoubleArray("y"), JInt("incy")),
    Routine   (            "sgemv", JString("trans"), JInt("m"), JInt("n"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloat("beta"), JFloatArray("y"), JInt("incy")),
    Routine   (            "dger", JInt("m"), JInt("n"), JDouble("alpha"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDoubleArray("y", "JNI_ABORT"), JInt("incy"), JDoubleArray("a"), JInt("lda")),
    Routine   (            "sger", JInt("m"), JInt("n"), JFloat("alpha"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloatArray("y", "JNI_ABORT"), JInt("incy"), JFloatArray("a"), JInt("lda")),
    RoutineR  (JDoubleR(), "dnrm2", JInt("n"), JDoubleArray("x", "JNI_ABORT"), JInt("incx")),
    RoutineR  (JFloatR(),  "snrm2", JInt("n"), JFloatArray("x", "JNI_ABORT"), JInt("incx")),
    Routine   (            "drot", JInt("n"), JDoubleArray("dx"), JInt("incx"), JDoubleArray("dy"), JInt("incy"), JDouble("c"), JDouble("s")),
    Routine   (            "srot", JInt("n"), JFloatArray("sx"), JInt("incx"), JFloatArray("sy"), JInt("incy"), JFloat("c"), JFloat("s")),
    Routine   (            "drotm", JInt("n"), JDoubleArray("dx", "JNI_ABORT"), JInt("incx"), JDoubleArray("dy"), JInt("incy"), JDoubleArray("dparam", "JNI_ABORT")),
    Routine   (            "srotm", JInt("n"), JFloatArray("sx", "JNI_ABORT"), JInt("incx"), JFloatArray("sy"), JInt("incy"), JFloatArray("sparam", "JNI_ABORT")),
    Routine   (            "drotmg", JDoubleW("dd1"), JDoubleW("dd2"), JDoubleW("dx1"), JDouble("dy1"), JDoubleArray("dparam", "JNI_ABORT")),
    Routine   (            "srotmg", JFloatW("sd1"), JFloatW("sd2"), JFloatW("sx1"), JFloat("sy1"), JFloatArray("sparam", "JNI_ABORT")),
    Routine   (            "dsbmv", JString("uplo"), JInt("n"), JInt("k"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDouble("beta"), JDoubleArray("y"), JInt("incy")),
    Routine   (            "ssbmv", JString("uplo"), JInt("n"), JInt("k"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloat("beta"), JFloatArray("y"), JInt("incy")),
    Routine   (            "dscal", JInt("n"), JDouble("alpha"), JDoubleArray("x"), JInt("incx")),
    Routine   (            "sscal", JInt("n"), JFloat("alpha"), JFloatArray("x"), JInt("incx")),
    Routine   (            "dspmv", JString("uplo"), JInt("n"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDouble("beta"), JDoubleArray("y"), JInt("incy")),
    Routine   (            "sspmv", JString("uplo"), JInt("n"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloat("beta"), JFloatArray("y"), JInt("incy")),
    Routine   (            "dspr", JString("uplo"), JInt("n"), JDouble("alpha"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDoubleArray("a")),
    Routine   (            "sspr", JString("uplo"), JInt("n"), JFloat("alpha"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloatArray("a")),
    Routine   (            "dspr2", JString("uplo"), JInt("n"), JDouble("alpha"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDoubleArray("y", "JNI_ABORT"), JInt("incy"), JDoubleArray("a")),
    Routine   (            "sspr2", JString("uplo"), JInt("n"), JFloat("alpha"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloatArray("y", "JNI_ABORT"), JInt("incy"), JFloatArray("a")),
    Routine   (            "dswap", JInt("n"), JDoubleArray("x"), JInt("incx"), JDoubleArray("y"), JInt("incy")),
    Routine   (            "sswap", JInt("n"), JFloatArray("x"), JInt("incx"), JFloatArray("y"), JInt("incy")),
    Routine   (            "dsymm", JString("side"), JString("uplo"), JInt("m"), JInt("n"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("b", "JNI_ABORT"), JInt("ldb"), JDouble("beta"), JDoubleArray("c"), JInt("ldc")),
    Routine   (            "ssymm", JString("side"), JString("uplo"), JInt("m"), JInt("n"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("b", "JNI_ABORT"), JInt("ldb"), JFloat("beta"), JFloatArray("c"), JInt("ldc")),
    Routine   (            "dsymv", JString("uplo"), JInt("n"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDouble("beta"), JDoubleArray("y"), JInt("incy")),
    Routine   (            "ssymv", JString("uplo"), JInt("n"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloat("beta"), JFloatArray("y"), JInt("incy")),
    Routine   (            "dsyr", JString("uplo"), JInt("n"), JDouble("alpha"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDoubleArray("a"), JInt("lda")),
    Routine   (            "ssyr", JString("uplo"), JInt("n"), JFloat("alpha"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloatArray("a"), JInt("lda")),
    Routine   (            "dsyr2", JString("uplo"), JInt("n"), JDouble("alpha"), JDoubleArray("x", "JNI_ABORT"), JInt("incx"), JDoubleArray("y", "JNI_ABORT"), JInt("incy"), JDoubleArray("a"), JInt("lda")),
    Routine   (            "ssyr2", JString("uplo"), JInt("n"), JFloat("alpha"), JFloatArray("x", "JNI_ABORT"), JInt("incx"), JFloatArray("y", "JNI_ABORT"), JInt("incy"), JFloatArray("a"), JInt("lda")),
    Routine   (            "dsyr2k", JString("uplo"), JString("trans"), JInt("n"), JInt("k"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("b", "JNI_ABORT"), JInt("ldb"), JDouble("beta"), JDoubleArray("c"), JInt("ldc")),
    Routine   (            "ssyr2k", JString("uplo"), JString("trans"), JInt("n"), JInt("k"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("b", "JNI_ABORT"), JInt("ldb"), JFloat("beta"), JFloatArray("c"), JInt("ldc")),
    Routine   (            "dsyrk", JString("uplo"), JString("trans"), JInt("n"), JInt("k"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDouble("beta"), JDoubleArray("c"), JInt("ldc")),
    Routine   (            "ssyrk", JString("uplo"), JString("trans"), JInt("n"), JInt("k"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloat("beta"), JFloatArray("c"), JInt("ldc")),
    Routine   (            "dtbmv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("k"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("x"), JInt("incx")),
    Routine   (            "stbmv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("k"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("x"), JInt("incx")),
    Routine   (            "dtbsv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("k"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("x"), JInt("incx")),
    Routine   (            "stbsv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("k"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("x"), JInt("incx")),
    Routine   (            "dtpmv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JDoubleArray("a", "JNI_ABORT"), JDoubleArray("x"), JInt("incx")),
    Routine   (            "stpmv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JFloatArray("a", "JNI_ABORT"), JFloatArray("x"), JInt("incx")),
    Routine   (            "dtpsv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JDoubleArray("a", "JNI_ABORT"), JDoubleArray("x"), JInt("incx")),
    Routine   (            "stpsv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JFloatArray("a", "JNI_ABORT"), JFloatArray("x"), JInt("incx")),
    Routine   (            "dtrmm", JString("side"), JString("uplo"), JString("transa"), JString("diag"), JInt("m"), JInt("n"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("b"), JInt("ldb")),
    Routine   (            "strmm", JString("side"), JString("uplo"), JString("transa"), JString("diag"), JInt("m"), JInt("n"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("b"), JInt("ldb")),
    Routine   (            "dtrmv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("x"), JInt("incx")),
    Routine   (            "strmv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("x"), JInt("incx")),
    Routine   (            "dtrsm", JString("side"), JString("uplo"), JString("transa"), JString("diag"), JInt("m"), JInt("n"), JDouble("alpha"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("b"), JInt("ldb")),
    Routine   (            "strsm", JString("side"), JString("uplo"), JString("transa"), JString("diag"), JInt("m"), JInt("n"), JFloat("alpha"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("b"), JInt("ldb")),
    Routine   (            "dtrsv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JDoubleArray("a", "JNI_ABORT"), JInt("lda"), JDoubleArray("x"), JInt("incx")),
    Routine   (            "strsv", JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JFloatArray("a", "JNI_ABORT"), JInt("lda"), JFloatArray("x"), JInt("incx")),
    RoutineR  (JIntR(),    "idamax", JInt("n"), JDoubleArray("dx", "JNI_ABORT"), JInt("incdx")),
    RoutineR  (JIntR(),    "isamax", JInt("n"), JFloatArray("sx", "JNI_ABORT"), JInt("incsx")),
  )

if sys.argv[1] == "lapack":
  Library("lapack", "liblapack.so.3",
    Routine   (             "dbdsdc",   JString("uplo"), JString("compq"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("vt"), JInt("ldvt"), JDoubleArray("q"), JIntArray("iq"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dbdsqr",   JString("uplo"), JInt("n"), JInt("ncvt"), JInt("nru"), JInt("ncc"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("vt"), JInt("ldvt"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "ddisna",   JString("job"), JInt("m"), JInt("n"), JDoubleArray("d"), JDoubleArray("sep"), JIntW("info")),
    Routine   (             "dgbbrd",   JString("vect"), JInt("m"), JInt("n"), JInt("ncc"), JInt("kl"), JInt("ku"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("pt"), JInt("ldpt"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dgbcon",   JString("norm"), JInt("n"), JInt("kl"), JInt("ku"), JDoubleArray("ab"), JInt("ldab"), JIntArray("ipiv"), JDouble("anorm"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgbequ",   JInt("m"), JInt("n"), JInt("kl"), JInt("ku"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("r"), JDoubleArray("c"), JDoubleW("rowcnd"), JDoubleW("colcnd"), JDoubleW("amax"), JIntW("info")),
    Routine   (             "dgbrfs",   JString("trans"), JInt("n"), JInt("kl"), JInt("ku"), JInt("nrhs"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("afb"), JInt("ldafb"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgbsv",    JInt("n"), JInt("kl"), JInt("ku"), JInt("nrhs"), JDoubleArray("ab"), JInt("ldab"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dgbsvx",   JString("fact"), JString("trans"), JInt("n"), JInt("kl"), JInt("ku"), JInt("nrhs"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("afb"), JInt("ldafb"), JIntArray("ipiv"), JStringW("equed"), JDoubleArray("r"), JDoubleArray("c"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleW("rcond"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgbtf2",   JInt("m"), JInt("n"), JInt("kl"), JInt("ku"), JDoubleArray("ab"), JInt("ldab"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "dgbtrf",   JInt("m"), JInt("n"), JInt("kl"), JInt("ku"), JDoubleArray("ab"), JInt("ldab"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "dgbtrs",   JString("trans"), JInt("n"), JInt("kl"), JInt("ku"), JInt("nrhs"), JDoubleArray("ab"), JInt("ldab"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dgebak",   JString("job"), JString("side"), JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("scale"), JInt("m"), JDoubleArray("v"), JInt("ldv"), JIntW("info")),
    Routine   (             "dgebal",   JString("job"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("ilo"), JIntW("ihi"), JDoubleArray("scale"), JIntW("info")),
    Routine   (             "dgebd2",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("tauq"), JDoubleArray("taup"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dgebrd",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("tauq"), JDoubleArray("taup"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgecon",   JString("norm"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDouble("anorm"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgeequ",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("r"), JDoubleArray("c"), JDoubleW("rowcnd"), JDoubleW("colcnd"), JDoubleW("amax"), JIntW("info")),
    Routine_NI(             "dgees",    JString("jobvs"), JString("sort"), JObject("select"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("sdim"), JDoubleArray("wr"), JDoubleArray("wi"), JDoubleArray("vs"), JInt("ldvs"), JDoubleArray("work"), JInt("lwork"), JBooleanArray("bwork"), JIntW("info")),
    Routine_NI(             "dgeesx",   JString("jobvs"), JString("sort"), JObject("select"), JString("sense"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("sdim"), JDoubleArray("wr"), JDoubleArray("wi"), JDoubleArray("vs"), JInt("ldvs"), JDoubleW("rconde"), JDoubleW("rcondv"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JBooleanArray("bwork"), JIntW("info")),
    Routine   (             "dgeev",    JString("jobvl"), JString("jobvr"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("wr"), JDoubleArray("wi"), JDoubleArray("vl"), JInt("ldvl"), JDoubleArray("vr"), JInt("ldvr"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgeevx",   JString("balanc"), JString("jobvl"), JString("jobvr"), JString("sense"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("wr"), JDoubleArray("wi"), JDoubleArray("vl"), JInt("ldvl"), JDoubleArray("vr"), JInt("ldvr"), JIntW("ilo"), JIntW("ihi"), JDoubleArray("scale"), JDoubleW("abnrm"), JDoubleArray("rconde"), JDoubleArray("rcondv"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgegs",    JString("jobvsl"), JString("jobvsr"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("alphar"), JDoubleArray("alphai"), JDoubleArray("beta"), JDoubleArray("vsl"), JInt("ldvsl"), JDoubleArray("vsr"), JInt("ldvsr"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgegv",    JString("jobvl"), JString("jobvr"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("alphar"), JDoubleArray("alphai"), JDoubleArray("beta"), JDoubleArray("vl"), JInt("ldvl"), JDoubleArray("vr"), JInt("ldvr"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgehd2",   JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dgehrd",   JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgelq2",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dgelqf",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgels",    JString("trans"), JInt("m"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgelsd",   JInt("m"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("s"), JDouble("rcond"), JIntW("rank"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgelss",   JInt("m"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("s"), JDouble("rcond"), JIntW("rank"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgelsx",   JInt("m"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JIntArray("jpvt"), JDouble("rcond"), JIntW("rank"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dgelsy",   JInt("m"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JIntArray("jpvt"), JDouble("rcond"), JIntW("rank"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgeql2",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dgeqlf",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgeqp3",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntArray("jpvt"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgeqpf",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntArray("jpvt"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dgeqr2",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dgeqrf",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgerfs",   JString("trans"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("af"), JInt("ldaf"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgerq2",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dgerqf",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgesc2",   JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("rhs"), JIntArray("ipiv"), JIntArray("jpiv"), JDoubleW("scale")),
    Routine   (             "dgesdd",   JString("jobz"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("s"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("vt"), JInt("ldvt"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgesv",    JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dgesvd",   JString("jobu"), JString("jobvt"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("s"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("vt"), JInt("ldvt"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgesvx",   JString("fact"), JString("trans"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("af"), JInt("ldaf"), JIntArray("ipiv"), JStringW("equed"), JDoubleArray("r"), JDoubleArray("c"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleW("rcond"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgetc2",   JInt("n"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JIntArray("jpiv"), JIntW("info")),
    Routine   (             "dgetf2",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "dgetrf",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "dgetri",   JInt("n"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgetrs",   JString("trans"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dggbak",   JString("job"), JString("side"), JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("lscale"), JDoubleArray("rscale"), JInt("m"), JDoubleArray("v"), JInt("ldv"), JIntW("info")),
    Routine   (             "dggbal",   JString("job"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JIntW("ilo"), JIntW("ihi"), JDoubleArray("lscale"), JDoubleArray("rscale"), JDoubleArray("work"), JIntW("info")),
    Routine_NI(             "dgges",    JString("jobvsl"), JString("jobvsr"), JString("sort"), JObject("selctg"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JIntW("sdim"), JDoubleArray("alphar"), JDoubleArray("alphai"), JDoubleArray("beta"), JDoubleArray("vsl"), JInt("ldvsl"), JDoubleArray("vsr"), JInt("ldvsr"), JDoubleArray("work"), JInt("lwork"), JBooleanArray("bwork"), JIntW("info")),
    Routine_NI(             "dggesx",   JString("jobvsl"), JString("jobvsr"), JString("sort"), JObject("selctg"), JString("sense"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JIntW("sdim"), JDoubleArray("alphar"), JDoubleArray("alphai"), JDoubleArray("beta"), JDoubleArray("vsl"), JInt("ldvsl"), JDoubleArray("vsr"), JInt("ldvsr"), JDoubleArray("rconde"), JDoubleArray("rcondv"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JBooleanArray("bwork"), JIntW("info")),
    Routine   (             "dggev",    JString("jobvl"), JString("jobvr"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("alphar"), JDoubleArray("alphai"), JDoubleArray("beta"), JDoubleArray("vl"), JInt("ldvl"), JDoubleArray("vr"), JInt("ldvr"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dggevx",   JString("balanc"), JString("jobvl"), JString("jobvr"), JString("sense"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("alphar"), JDoubleArray("alphai"), JDoubleArray("beta"), JDoubleArray("vl"), JInt("ldvl"), JDoubleArray("vr"), JInt("ldvr"), JIntW("ilo"), JIntW("ihi"), JDoubleArray("lscale"), JDoubleArray("rscale"), JDoubleW("abnrm"), JDoubleW("bbnrm"), JDoubleArray("rconde"), JDoubleArray("rcondv"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JBooleanArray("bwork"), JIntW("info")),
    Routine   (             "dggglm",   JInt("n"), JInt("m"), JInt("p"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("d"), JDoubleArray("x"), JDoubleArray("y"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dgghrd",   JString("compq"), JString("compz"), JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("z"), JInt("ldz"), JIntW("info")),
    Routine   (             "dgglse",   JInt("m"), JInt("n"), JInt("p"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("c"), JDoubleArray("d"), JDoubleArray("x"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dggqrf",   JInt("n"), JInt("m"), JInt("p"), JDoubleArray("a"), JInt("lda"), JDoubleArray("taua"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("taub"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dggrqf",   JInt("m"), JInt("p"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("taua"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("taub"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dggsvd",   JString("jobu"), JString("jobv"), JString("jobq"), JInt("m"), JInt("n"), JInt("p"), JIntW("k"), JIntW("l"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("alpha"), JDoubleArray("beta"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dggsvp",   JString("jobu"), JString("jobv"), JString("jobq"), JInt("m"), JInt("p"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDouble("tola"), JDouble("tolb"), JIntW("k"), JIntW("l"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("q"), JInt("ldq"), JIntArray("iwork"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dgtcon",   JString("norm"), JInt("n"), JDoubleArray("dl"), JDoubleArray("d"), JDoubleArray("du"), JDoubleArray("du2"), JIntArray("ipiv"), JDouble("anorm"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgtrfs",   JString("trans"), JInt("n"), JInt("nrhs"), JDoubleArray("dl"), JDoubleArray("d"), JDoubleArray("du"), JDoubleArray("dlf"), JDoubleArray("df"), JDoubleArray("duf"), JDoubleArray("du2"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgtsv",    JInt("n"), JInt("nrhs"), JDoubleArray("dl"), JDoubleArray("d"), JDoubleArray("du"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dgtsvx",   JString("fact"), JString("trans"), JInt("n"), JInt("nrhs"), JDoubleArray("dl"), JDoubleArray("d"), JDoubleArray("du"), JDoubleArray("dlf"), JDoubleArray("df"), JDoubleArray("duf"), JDoubleArray("du2"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleW("rcond"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dgttrf",   JInt("n"), JDoubleArray("dl"), JDoubleArray("d"), JDoubleArray("du"), JDoubleArray("du2"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "dgttrs",   JString("trans"), JInt("n"), JInt("nrhs"), JDoubleArray("dl"), JDoubleArray("d"), JDoubleArray("du"), JDoubleArray("du2"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dgtts2",   JInt("itrans"), JInt("n"), JInt("nrhs"), JDoubleArray("dl"), JDoubleArray("d"), JDoubleArray("du"), JDoubleArray("du2"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb")),
    Routine   (             "dhgeqz",   JString("job"), JString("compq"), JString("compz"), JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("alphar"), JDoubleArray("alphai"), JDoubleArray("beta"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dhsein",   JString("side"), JString("eigsrc"), JString("initv"), JBooleanArray("select"), JInt("n"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("wr"), JDoubleArray("wi"), JDoubleArray("vl"), JInt("ldvl"), JDoubleArray("vr"), JInt("ldvr"), JInt("mm"), JIntW("m"), JDoubleArray("work"), JIntArray("ifaill"), JIntArray("ifailr"), JIntW("info")),
    Routine   (             "dhseqr",   JString("job"), JString("compz"), JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("wr"), JDoubleArray("wi"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    RoutineR  (JBooleanR(), "disnan",   JDouble("din")),
    Routine   (             "dlabad",   JDoubleW("small"), JDoubleW("large")),
    Routine   (             "dlabrd",   JInt("m"), JInt("n"), JInt("nb"), JDoubleArray("a"), JInt("lda"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("tauq"), JDoubleArray("taup"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("y"), JInt("ldy")),
    Routine   (             "dlacn2",   JInt("n"), JDoubleArray("v"), JDoubleArray("x"), JIntArray("isgn"), JDoubleW("est"), JIntW("kase"), JIntArray("isave")),
    Routine   (             "dlacon",   JInt("n"), JDoubleArray("v"), JDoubleArray("x"), JIntArray("isgn"), JDoubleW("est"), JIntW("kase")),
    Routine   (             "dlacpy",   JString("uplo"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb")),
    Routine   (             "dladiv",   JDouble("a"), JDouble("b"), JDouble("c"), JDouble("d"), JDoubleW("p"), JDoubleW("q")),
    Routine   (             "dlae2",    JDouble("a"), JDouble("b"), JDouble("c"), JDoubleW("rt1"), JDoubleW("rt2")),
    Routine   (             "dlaebz",   JInt("ijob"), JInt("nitmax"), JInt("n"), JInt("mmax"), JInt("minp"), JInt("nbmin"), JDouble("abstol"), JDouble("reltol"), JDouble("pivmin"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("e2"), JIntArray("nval"), JDoubleArray("ab"), JDoubleArray("c"), JIntW("mout"), JIntArray("nab"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlaed0",   JInt("icompq"), JInt("qsiz"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("qstore"), JInt("ldqs"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlaed1",   JInt("n"), JDoubleArray("d"), JDoubleArray("q"), JInt("ldq"), JIntArray("indxq"), JDoubleW("rho"), JInt("cutpnt"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlaed2",   JIntW("k"), JInt("n"), JInt("n1"), JDoubleArray("d"), JDoubleArray("q"), JInt("ldq"), JIntArray("indxq"), JDoubleW("rho"), JDoubleArray("z"), JDoubleArray("dlamda"), JDoubleArray("w"), JDoubleArray("q2"), JIntArray("indx"), JIntArray("indxc"), JIntArray("indxp"), JIntArray("coltyp"), JIntW("info")),
    Routine   (             "dlaed3",   JInt("k"), JInt("n"), JInt("n1"), JDoubleArray("d"), JDoubleArray("q"), JInt("ldq"), JDouble("rho"), JDoubleArray("dlamda"), JDoubleArray("q2"), JIntArray("indx"), JIntArray("ctot"), JDoubleArray("w"), JDoubleArray("s"), JIntW("info")),
    Routine   (             "dlaed4",   JInt("n"), JInt("i"), JDoubleArray("d"), JDoubleArray("z"), JDoubleArray("delta"), JDouble("rho"), JDoubleW("dlam"), JIntW("info")),
    Routine   (             "dlaed5",   JInt("i"), JDoubleArray("d"), JDoubleArray("z"), JDoubleArray("delta"), JDouble("rho"), JDoubleW("dlam")),
    Routine   (             "dlaed6",   JInt("kniter"), JBoolean("orgati"), JDouble("rho"), JDoubleArray("d"), JDoubleArray("z"), JDouble("finit"), JDoubleW("tau"), JIntW("info")),
    Routine   (             "dlaed7",   JInt("icompq"), JInt("n"), JInt("qsiz"), JInt("tlvls"), JInt("curlvl"), JInt("curpbm"), JDoubleArray("d"), JDoubleArray("q"), JInt("ldq"), JIntArray("indxq"), JDoubleW("rho"), JInt("cutpnt"), JDoubleArray("qstore"), JIntArray("qptr"), JIntArray("prmptr"), JIntArray("perm"), JIntArray("givptr"), JIntArray("givcol"), JDoubleArray("givnum"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlaed8",   JInt("icompq"), JIntW("k"), JInt("n"), JInt("qsiz"), JDoubleArray("d"), JDoubleArray("q"), JInt("ldq"), JIntArray("indxq"), JDoubleW("rho"), JInt("cutpnt"), JDoubleArray("z"), JDoubleArray("dlamda"), JDoubleArray("q2"), JInt("ldq2"), JDoubleArray("w"), JIntArray("perm"), JIntW("givptr"), JIntArray("givcol"), JDoubleArray("givnum"), JIntArray("indxp"), JIntArray("indx"), JIntW("info")),
    Routine   (             "dlaed9",   JInt("k"), JInt("kstart"), JInt("kstop"), JInt("n"), JDoubleArray("d"), JDoubleArray("q"), JInt("ldq"), JDouble("rho"), JDoubleArray("dlamda"), JDoubleArray("w"), JDoubleArray("s"), JInt("lds"), JIntW("info")),
    Routine   (             "dlaeda",   JInt("n"), JInt("tlvls"), JInt("curlvl"), JInt("curpbm"), JIntArray("prmptr"), JIntArray("perm"), JIntArray("givptr"), JIntArray("givcol"), JDoubleArray("givnum"), JDoubleArray("q"), JIntArray("qptr"), JDoubleArray("z"), JDoubleArray("ztemp"), JIntW("info")),
    Routine   (             "dlaein",   JBoolean("rightv"), JBoolean("noinit"), JInt("n"), JDoubleArray("h"), JInt("ldh"), JDouble("wr"), JDouble("wi"), JDoubleArray("vr"), JDoubleArray("vi"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("work"), JDouble("eps3"), JDouble("smlnum"), JDouble("bignum"), JIntW("info")),
    Routine   (             "dlaev2",   JDouble("a"), JDouble("b"), JDouble("c"), JDoubleW("rt1"), JDoubleW("rt2"), JDoubleW("cs1"), JDoubleW("sn1")),
    Routine   (             "dlaexc",   JBoolean("wantq"), JInt("n"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("q"), JInt("ldq"), JInt("j1"), JInt("n1"), JInt("n2"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dlag2",    JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDouble("safmin"), JDoubleW("scale1"), JDoubleW("scale2"), JDoubleW("wr1"), JDoubleW("wr2"), JDoubleW("wi")),
    Routine   (             "dlag2s",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JFloatArray("sa"), JInt("ldsa"), JIntW("info")),
    Routine   (             "dlags2",   JBoolean("upper"), JDouble("a1"), JDouble("a2"), JDouble("a3"), JDouble("b1"), JDouble("b2"), JDouble("b3"), JDoubleW("csu"), JDoubleW("snu"), JDoubleW("csv"), JDoubleW("snv"), JDoubleW("csq"), JDoubleW("snq")),
    Routine   (             "dlagtf",   JInt("n"), JDoubleArray("a"), JDouble("lambda"), JDoubleArray("b"), JDoubleArray("c"), JDouble("tol"), JDoubleArray("d"), JIntArray("in"), JIntW("info")),
    Routine   (             "dlagtm",   JString("trans"), JInt("n"), JInt("nrhs"), JDouble("alpha"), JDoubleArray("dl"), JDoubleArray("d"), JDoubleArray("du"), JDoubleArray("x"), JInt("ldx"), JDouble("beta"), JDoubleArray("b"), JInt("ldb")),
    Routine   (             "dlagts",   JInt("job"), JInt("n"), JDoubleArray("a"), JDoubleArray("b"), JDoubleArray("c"), JDoubleArray("d"), JIntArray("in"), JDoubleArray("y"), JDoubleW("tol"), JIntW("info")),
    Routine   (             "dlagv2",   JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("alphar"), JDoubleArray("alphai"), JDoubleArray("beta"), JDoubleW("csl"), JDoubleW("snl"), JDoubleW("csr"), JDoubleW("snr")),
    Routine   (             "dlahqr",   JBoolean("wantt"), JBoolean("wantz"), JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("wr"), JDoubleArray("wi"), JInt("iloz"), JInt("ihiz"), JDoubleArray("z"), JInt("ldz"), JIntW("info")),
    Routine   (             "dlahr2",   JInt("n"), JInt("k"), JInt("nb"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("y"), JInt("ldy")),
    Routine   (             "dlahrd",   JInt("n"), JInt("k"), JInt("nb"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("y"), JInt("ldy")),
    Routine   (             "dlaic1",   JInt("job"), JInt("j"), JDoubleArray("x"), JDouble("sest"), JDoubleArray("w"), JDouble("gamma"), JDoubleW("sestpr"), JDoubleW("s"), JDoubleW("c")),
    RoutineR  (JBooleanR(), "dlaisnan", JDouble("din1"), JDouble("din2")),
    Routine   (             "dlaln2",   JBoolean("ltrans"), JInt("na"), JInt("nw"), JDouble("smin"), JDouble("ca"), JDoubleArray("a"), JInt("lda"), JDouble("d1"), JDouble("d2"), JDoubleArray("b"), JInt("ldb"), JDouble("wr"), JDouble("wi"), JDoubleArray("x"), JInt("ldx"), JDoubleW("scale"), JDoubleW("xnorm"), JIntW("info")),
    Routine   (             "dlals0",   JInt("icompq"), JInt("nl"), JInt("nr"), JInt("sqre"), JInt("nrhs"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("bx"), JInt("ldbx"), JIntArray("perm"), JInt("givptr"), JIntArray("givcol"), JInt("ldgcol"), JDoubleArray("givnum"), JInt("ldgnum"), JDoubleArray("poles"), JDoubleArray("difl"), JDoubleArray("difr"), JDoubleArray("z"), JInt("k"), JDouble("c"), JDouble("s"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dlalsa",   JInt("icompq"), JInt("smlsiz"), JInt("n"), JInt("nrhs"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("bx"), JInt("ldbx"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("vt"), JIntArray("k"), JDoubleArray("difl"), JDoubleArray("difr"), JDoubleArray("z"), JDoubleArray("poles"), JIntArray("givptr"), JIntArray("givcol"), JInt("ldgcol"), JIntArray("perm"), JDoubleArray("givnum"), JDoubleArray("c"), JDoubleArray("s"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlalsd",   JString("uplo"), JInt("smlsiz"), JInt("n"), JInt("nrhs"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("b"), JInt("ldb"), JDouble("rcond"), JIntW("rank"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlamrg",   JInt("n1"), JInt("n2"), JDoubleArray("a"), JInt("dtrd1"), JInt("dtrd2"), JIntArray("index")),
    RoutineR  (JIntR(),     "dlaneg",   JInt("n"), JDoubleArray("d"), JDoubleArray("lld"), JDouble("sigma"), JDouble("pivmin"), JInt("r")),
    RoutineR  (JDoubleR(),  "dlangb",   JString("norm"), JInt("n"), JInt("kl"), JInt("ku"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("work")),
    RoutineR  (JDoubleR(),  "dlange",   JString("norm"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("work")),
    RoutineR  (JDoubleR(),  "dlangt",   JString("norm"), JInt("n"), JDoubleArray("dl"), JDoubleArray("d"), JDoubleArray("du")),
    RoutineR  (JDoubleR(),  "dlanhs",   JString("norm"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("work")),
    RoutineR  (JDoubleR(),  "dlansb",   JString("norm"), JString("uplo"), JInt("n"), JInt("k"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("work")),
    RoutineR  (JDoubleR(),  "dlansp",   JString("norm"), JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("work")),
    RoutineR  (JDoubleR(),  "dlanst",   JString("norm"), JInt("n"), JDoubleArray("d"), JDoubleArray("e")),
    RoutineR  (JDoubleR(),  "dlansy",   JString("norm"), JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("work")),
    RoutineR  (JDoubleR(),  "dlantb",   JString("norm"), JString("uplo"), JString("diag"), JInt("n"), JInt("k"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("work")),
    RoutineR  (JDoubleR(),  "dlantp",   JString("norm"), JString("uplo"), JString("diag"), JInt("n"), JDoubleArray("ap"), JDoubleArray("work")),
    RoutineR  (JDoubleR(),  "dlantr",   JString("norm"), JString("uplo"), JString("diag"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("work")),
    Routine   (             "dlanv2",   JDoubleW("a"), JDoubleW("b"), JDoubleW("c"), JDoubleW("d"), JDoubleW("rt1r"), JDoubleW("rt1i"), JDoubleW("rt2r"), JDoubleW("rt2i"), JDoubleW("cs"), JDoubleW("sn")),
    Routine   (             "dlapll",   JInt("n"), JDoubleArray("x"), JInt("incx"), JDoubleArray("y"), JInt("incy"), JDoubleW("ssmin")),
    Routine   (             "dlapmt",   JBoolean("forwrd"), JInt("m"), JInt("n"), JDoubleArray("x"), JInt("ldx"), JIntArray("k")),
    RoutineR  (JDoubleR(),  "dlapy2",   JDouble("x"), JDouble("y")),
    RoutineR  (JDoubleR(),  "dlapy3",   JDouble("x"), JDouble("y"), JDouble("z")),
    Routine   (             "dlaqgb",   JInt("m"), JInt("n"), JInt("kl"), JInt("ku"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("r"), JDoubleArray("c"), JDouble("rowcnd"), JDouble("colcnd"), JDouble("amax"), JStringW("equed")),
    Routine   (             "dlaqge",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("r"), JDoubleArray("c"), JDouble("rowcnd"), JDouble("colcnd"), JDouble("amax"), JStringW("equed")),
    Routine   (             "dlaqp2",   JInt("m"), JInt("n"), JInt("offset"), JDoubleArray("a"), JInt("lda"), JIntArray("jpvt"), JDoubleArray("tau"), JDoubleArray("vn1"), JDoubleArray("vn2"), JDoubleArray("work")),
    Routine   (             "dlaqps",   JInt("m"), JInt("n"), JInt("offset"), JInt("nb"), JIntW("kb"), JDoubleArray("a"), JInt("lda"), JIntArray("jpvt"), JDoubleArray("tau"), JDoubleArray("vn1"), JDoubleArray("vn2"), JDoubleArray("auxv"), JDoubleArray("f"), JInt("ldf")),
    Routine   (             "dlaqr0",   JBoolean("wantt"), JBoolean("wantz"), JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("wr"), JDoubleArray("wi"), JInt("iloz"), JInt("ihiz"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dlaqr1",   JInt("n"), JDoubleArray("h"), JInt("ldh"), JDouble("sr1"), JDouble("si1"), JDouble("sr2"), JDouble("si2"), JDoubleArray("v")),
    Routine   (             "dlaqr2",   JBoolean("wantt"), JBoolean("wantz"), JInt("n"), JInt("ktop"), JInt("kbot"), JInt("nw"), JDoubleArray("h"), JInt("ldh"), JInt("iloz"), JInt("ihiz"), JDoubleArray("z"), JInt("ldz"), JIntW("ns"), JIntW("nd"), JDoubleArray("sr"), JDoubleArray("si"), JDoubleArray("v"), JInt("ldv"), JInt("nh"), JDoubleArray("t"), JInt("ldt"), JInt("nv"), JDoubleArray("wv"), JInt("ldwv"), JDoubleArray("work"), JInt("lwork")),
    Routine   (             "dlaqr3",   JBoolean("wantt"), JBoolean("wantz"), JInt("n"), JInt("ktop"), JInt("kbot"), JInt("nw"), JDoubleArray("h"), JInt("ldh"), JInt("iloz"), JInt("ihiz"), JDoubleArray("z"), JInt("ldz"), JIntW("ns"), JIntW("nd"), JDoubleArray("sr"), JDoubleArray("si"), JDoubleArray("v"), JInt("ldv"), JInt("nh"), JDoubleArray("t"), JInt("ldt"), JInt("nv"), JDoubleArray("wv"), JInt("ldwv"), JDoubleArray("work"), JInt("lwork")),
    Routine   (             "dlaqr4",   JBoolean("wantt"), JBoolean("wantz"), JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("wr"), JDoubleArray("wi"), JInt("iloz"), JInt("ihiz"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dlaqr5",   JBoolean("wantt"), JBoolean("wantz"), JInt("kacc22"), JInt("n"), JInt("ktop"), JInt("kbot"), JInt("nshfts"), JDoubleArray("sr"), JDoubleArray("si"), JDoubleArray("h"), JInt("ldh"), JInt("iloz"), JInt("ihiz"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("u"), JInt("ldu"), JInt("nv"), JDoubleArray("wv"), JInt("ldwv"), JInt("nh"), JDoubleArray("wh"), JInt("ldwh")),
    Routine   (             "dlaqsb",   JString("uplo"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("s"), JDouble("scond"), JDouble("amax"), JStringW("equed")),
    Routine   (             "dlaqsp",   JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("s"), JDouble("scond"), JDouble("amax"), JStringW("equed")),
    Routine   (             "dlaqsy",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("s"), JDouble("scond"), JDouble("amax"), JStringW("equed")),
    Routine   (             "dlaqtr",   JBoolean("ltran"), JBoolean("lreal"), JInt("n"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("b"), JDouble("w"), JDoubleW("scale"), JDoubleArray("x"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dlar1v",   JInt("n"), JInt("b1"), JInt("bn"), JDouble("lambda"), JDoubleArray("d"), JDoubleArray("l"), JDoubleArray("ld"), JDoubleArray("lld"), JDouble("pivmin"), JDouble("gaptol"), JDoubleArray("z"), JBoolean("wantnc"), JIntW("negcnt"), JDoubleW("ztz"), JDoubleW("mingma"), JIntW("r"), JIntArray("isuppz"), JDoubleW("nrminv"), JDoubleW("resid"), JDoubleW("rqcorr"), JDoubleArray("work")),
    Routine   (             "dlar2v",   JInt("n"), JDoubleArray("x"), JDoubleArray("y"), JDoubleArray("z"), JInt("incx"), JDoubleArray("c"), JDoubleArray("s"), JInt("incc")),
    Routine   (             "dlarf",    JString("side"), JInt("m"), JInt("n"), JDoubleArray("v"), JInt("incv"), JDouble("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work")),
    Routine   (             "dlarfb",   JString("side"), JString("trans"), JString("direct"), JString("storev"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JInt("ldwork")),
    Routine   (             "dlarfg",   JInt("n"), JDoubleW("alpha"), JDoubleArray("x"), JInt("incx"), JDoubleW("tau")),
    Routine   (             "dlarft",   JString("direct"), JString("storev"), JInt("n"), JInt("k"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("tau"), JDoubleArray("t"), JInt("ldt")),
    Routine   (             "dlarfx",   JString("side"), JInt("m"), JInt("n"), JDoubleArray("v"), JDouble("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work")),
    Routine   (             "dlargv",   JInt("n"), JDoubleArray("x"), JInt("incx"), JDoubleArray("y"), JInt("incy"), JDoubleArray("c"), JInt("incc")),
    Routine   (             "dlarnv",   JInt("idist"), JIntArray("iseed"), JInt("n"), JDoubleArray("x")),
    Routine   (             "dlarra",   JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("e2"), JDouble("spltol"), JDouble("tnrm"), JIntW("nsplit"), JIntArray("isplit"), JIntW("info")),
    Routine   (             "dlarrb",   JInt("n"), JDoubleArray("d"), JDoubleArray("lld"), JInt("ifirst"), JInt("ilast"), JDouble("rtol1"), JDouble("rtol2"), JInt("offset"), JDoubleArray("w"), JDoubleArray("wgap"), JDoubleArray("werr"), JDoubleArray("work"), JIntArray("iwork"), JDouble("pivmin"), JDouble("spdiam"), JInt("twist"), JIntW("info")),
    Routine   (             "dlarrc",   JString("jobt"), JInt("n"), JDouble("vl"), JDouble("vu"), JDoubleArray("d"), JDoubleArray("e"), JDouble("pivmin"), JIntW("eigcnt"), JIntW("lcnt"), JIntW("rcnt"), JIntW("info")),
    Routine   (             "dlarrd",   JString("range"), JString("order"), JInt("n"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDoubleArray("gers"), JDouble("reltol"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("e2"), JDouble("pivmin"), JInt("nsplit"), JIntArray("isplit"), JIntW("m"), JDoubleArray("w"), JDoubleArray("werr"), JDoubleW("wl"), JDoubleW("wu"), JIntArray("iblock"), JIntArray("indexw"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlarre",   JString("range"), JInt("n"), JDoubleW("vl"), JDoubleW("vu"), JInt("il"), JInt("iu"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("e2"), JDouble("rtol1"), JDouble("rtol2"), JDouble("spltol"), JIntW("nsplit"), JIntArray("isplit"), JIntW("m"), JDoubleArray("w"), JDoubleArray("werr"), JDoubleArray("wgap"), JIntArray("iblock"), JIntArray("indexw"), JDoubleArray("gers"), JDoubleW("pivmin"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlarrf",   JInt("n"), JDoubleArray("d"), JDoubleArray("l"), JDoubleArray("ld"), JInt("clstrt"), JInt("clend"), JDoubleArray("w"), JDoubleArray("wgap"), JDoubleArray("werr"), JDouble("spdiam"), JDouble("clgapl"), JDouble("clgapr"), JDouble("pivmin"), JDoubleW("sigma"), JDoubleArray("dplus"), JDoubleArray("lplus"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dlarrj",   JInt("n"), JDoubleArray("d"), JDoubleArray("e2"), JInt("ifirst"), JInt("ilast"), JDouble("rtol"), JInt("offset"), JDoubleArray("w"), JDoubleArray("werr"), JDoubleArray("work"), JIntArray("iwork"), JDouble("pivmin"), JDouble("spdiam"), JIntW("info")),
    Routine   (             "dlarrk",   JInt("n"), JInt("iw"), JDouble("gl"), JDouble("gu"), JDoubleArray("d"), JDoubleArray("e2"), JDouble("pivmin"), JDouble("reltol"), JDoubleW("w"), JDoubleW("werr"), JIntW("info")),
    Routine   (             "dlarrr",   JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JIntW("info")),
    Routine   (             "dlarrv",   JInt("n"), JDouble("vl"), JDouble("vu"), JDoubleArray("d"), JDoubleArray("l"), JDouble("pivmin"), JIntArray("isplit"), JInt("m"), JInt("dol"), JInt("dou"), JDouble("minrgp"), JDoubleW("rtol1"), JDoubleW("rtol2"), JDoubleArray("w"), JDoubleArray("werr"), JDoubleArray("wgap"), JIntArray("iblock"), JIntArray("indexw"), JDoubleArray("gers"), JDoubleArray("z"), JInt("ldz"), JIntArray("isuppz"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlartg",   JDouble("f"), JDouble("g"), JDoubleW("cs"), JDoubleW("sn"), JDoubleW("r")),
    Routine   (             "dlartv",   JInt("n"), JDoubleArray("x"), JInt("incx"), JDoubleArray("y"), JInt("incy"), JDoubleArray("c"), JDoubleArray("s"), JInt("incc")),
    Routine   (             "dlaruv",   JIntArray("iseed"), JInt("n"), JDoubleArray("x")),
    Routine   (             "dlarz",    JString("side"), JInt("m"), JInt("n"), JInt("l"), JDoubleArray("v"), JInt("incv"), JDouble("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work")),
    Routine   (             "dlarzb",   JString("side"), JString("trans"), JString("direct"), JString("storev"), JInt("m"), JInt("n"), JInt("k"), JInt("l"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JInt("ldwork")),
    Routine   (             "dlarzt",   JString("direct"), JString("storev"), JInt("n"), JInt("k"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("tau"), JDoubleArray("t"), JInt("ldt")),
    Routine   (             "dlas2",    JDouble("f"), JDouble("g"), JDouble("h"), JDoubleW("ssmin"), JDoubleW("ssmax")),
    Routine   (             "dlascl",   JString("type"), JInt("kl"), JInt("ku"), JDouble("cfrom"), JDouble("cto"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "dlasd0",   JInt("n"), JInt("sqre"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("vt"), JInt("ldvt"), JInt("smlsiz"), JIntArray("iwork"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dlasd1",   JInt("nl"), JInt("nr"), JInt("sqre"), JDoubleArray("d"), JDoubleW("alpha"), JDoubleW("beta"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("vt"), JInt("ldvt"), JIntArray("idxq"), JIntArray("iwork"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dlasd2",   JInt("nl"), JInt("nr"), JInt("sqre"), JIntW("k"), JDoubleArray("d"), JDoubleArray("z"), JDouble("alpha"), JDouble("beta"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("vt"), JInt("ldvt"), JDoubleArray("dsigma"), JDoubleArray("u2"), JInt("ldu2"), JDoubleArray("vt2"), JInt("ldvt2"), JIntArray("idxp"), JIntArray("idx"), JIntArray("idxc"), JIntArray("idxq"), JIntArray("coltyp"), JIntW("info")),
    Routine   (             "dlasd3",   JInt("nl"), JInt("nr"), JInt("sqre"), JInt("k"), JDoubleArray("d"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("dsigma"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("u2"), JInt("ldu2"), JDoubleArray("vt"), JInt("ldvt"), JDoubleArray("vt2"), JInt("ldvt2"), JIntArray("idxc"), JIntArray("ctot"), JDoubleArray("z"), JIntW("info")),
    Routine   (             "dlasd4",   JInt("n"), JInt("i"), JDoubleArray("d"), JDoubleArray("z"), JDoubleArray("delta"), JDouble("rho"), JDoubleW("sigma"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dlasd5",   JInt("i"), JDoubleArray("d"), JDoubleArray("z"), JDoubleArray("delta"), JDouble("rho"), JDoubleW("dsigma"), JDoubleArray("work")),
    Routine   (             "dlasd6",   JInt("icompq"), JInt("nl"), JInt("nr"), JInt("sqre"), JDoubleArray("d"), JDoubleArray("vf"), JDoubleArray("vl"), JDoubleW("alpha"), JDoubleW("beta"), JIntArray("idxq"), JIntArray("perm"), JIntW("givptr"), JIntArray("givcol"), JInt("ldgcol"), JDoubleArray("givnum"), JInt("ldgnum"), JDoubleArray("poles"), JDoubleArray("difl"), JDoubleArray("difr"), JDoubleArray("z"), JIntW("k"), JDoubleW("c"), JDoubleW("s"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlasd7",   JInt("icompq"), JInt("nl"), JInt("nr"), JInt("sqre"), JIntW("k"), JDoubleArray("d"), JDoubleArray("z"), JDoubleArray("zw"), JDoubleArray("vf"), JDoubleArray("vfw"), JDoubleArray("vl"), JDoubleArray("vlw"), JDouble("alpha"), JDouble("beta"), JDoubleArray("dsigma"), JIntArray("idx"), JIntArray("idxp"), JIntArray("idxq"), JIntArray("perm"), JIntW("givptr"), JIntArray("givcol"), JInt("ldgcol"), JDoubleArray("givnum"), JInt("ldgnum"), JDoubleW("c"), JDoubleW("s"), JIntW("info")),
    Routine   (             "dlasd8",   JInt("icompq"), JInt("k"), JDoubleArray("d"), JDoubleArray("z"), JDoubleArray("vf"), JDoubleArray("vl"), JDoubleArray("difl"), JDoubleArray("difr"), JInt("lddifr"), JDoubleArray("dsigma"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dlasda",   JInt("icompq"), JInt("smlsiz"), JInt("n"), JInt("sqre"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("vt"), JIntArray("k"), JDoubleArray("difl"), JDoubleArray("difr"), JDoubleArray("z"), JDoubleArray("poles"), JIntArray("givptr"), JIntArray("givcol"), JInt("ldgcol"), JIntArray("perm"), JDoubleArray("givnum"), JDoubleArray("c"), JDoubleArray("s"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dlasdq",   JString("uplo"), JInt("sqre"), JInt("n"), JInt("ncvt"), JInt("nru"), JInt("ncc"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("vt"), JInt("ldvt"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dlasdt",   JInt("n"), JIntW("lvl"), JIntW("nd"), JIntArray("inode"), JIntArray("ndiml"), JIntArray("ndimr"), JInt("msub")),
    Routine   (             "dlaset",   JString("uplo"), JInt("m"), JInt("n"), JDouble("alpha"), JDouble("beta"), JDoubleArray("a"), JInt("lda")),
    Routine   (             "dlasq1",   JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dlasq2",   JInt("n"), JDoubleArray("z"), JIntW("info")),
    Routine   (             "dlasq3",   JInt("i0"), JIntW("n0"), JDoubleArray("z"), JInt("pp"), JDoubleW("dmin"), JDoubleW("sigma"), JDoubleW("desig"), JDoubleW("qmax"), JIntW("nfail"), JIntW("iter"), JIntW("ndiv"), JBoolean("ieee")),
    Routine   (             "dlasq4",   JInt("i0"), JInt("n0"), JDoubleArray("z"), JInt("pp"), JInt("n0in"), JDouble("dmin"), JDouble("dmin1"), JDouble("dmin2"), JDouble("dn"), JDouble("dn1"), JDouble("dn2"), JDoubleW("tau"), JIntW("ttype")),
    Routine   (             "dlasq5",   JInt("i0"), JInt("n0"), JDoubleArray("z"), JInt("pp"), JDouble("tau"), JDoubleW("dmin"), JDoubleW("dmin1"), JDoubleW("dmin2"), JDoubleW("dn"), JDoubleW("dnm1"), JDoubleW("dnm2"), JBoolean("ieee")),
    Routine   (             "dlasq6",   JInt("i0"), JInt("n0"), JDoubleArray("z"), JInt("pp"), JDoubleW("dmin"), JDoubleW("dmin1"), JDoubleW("dmin2"), JDoubleW("dn"), JDoubleW("dnm1"), JDoubleW("dnm2")),
    Routine   (             "dlasr",    JString("side"), JString("pivot"), JString("direct"), JInt("m"), JInt("n"), JDoubleArray("c"), JDoubleArray("s"), JDoubleArray("a"), JInt("lda")),
    Routine   (             "dlasrt",   JString("id"), JInt("n"), JDoubleArray("d"), JIntW("info")),
    Routine   (             "dlassq",   JInt("n"), JDoubleArray("x"), JInt("incx"), JDoubleW("scale"), JDoubleW("sumsq")),
    Routine   (             "dlasv2",   JDouble("f"), JDouble("g"), JDouble("h"), JDoubleW("ssmin"), JDoubleW("ssmax"), JDoubleW("snr"), JDoubleW("csr"), JDoubleW("snl"), JDoubleW("csl")),
    Routine   (             "dlaswp",   JInt("n"), JDoubleArray("a"), JInt("lda"), JInt("k1"), JInt("k2"), JIntArray("ipiv"), JInt("incx")),
    Routine   (             "dlasy2",   JBoolean("ltranl"), JBoolean("ltranr"), JInt("isgn"), JInt("n1"), JInt("n2"), JDoubleArray("tl"), JInt("ldtl"), JDoubleArray("tr"), JInt("ldtr"), JDoubleArray("b"), JInt("ldb"), JDoubleW("scale"), JDoubleArray("x"), JInt("ldx"), JDoubleW("xnorm"), JIntW("info")),
    Routine   (             "dlasyf",   JString("uplo"), JInt("n"), JInt("nb"), JIntW("kb"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JDoubleArray("w"), JInt("ldw"), JIntW("info")),
    Routine   (             "dlatbs",   JString("uplo"), JString("trans"), JString("diag"), JString("normin"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("x"), JDoubleW("scale"), JDoubleArray("cnorm"), JIntW("info")),
    Routine   (             "dlatdf",   JInt("ijob"), JInt("n"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("rhs"), JDoubleW("rdsum"), JDoubleW("rdscal"), JIntArray("ipiv"), JIntArray("jpiv")),
    Routine   (             "dlatps",   JString("uplo"), JString("trans"), JString("diag"), JString("normin"), JInt("n"), JDoubleArray("ap"), JDoubleArray("x"), JDoubleW("scale"), JDoubleArray("cnorm"), JIntW("info")),
    Routine   (             "dlatrd",   JString("uplo"), JInt("n"), JInt("nb"), JDoubleArray("a"), JInt("lda"), JDoubleArray("e"), JDoubleArray("tau"), JDoubleArray("w"), JInt("ldw")),
    Routine   (             "dlatrs",   JString("uplo"), JString("trans"), JString("diag"), JString("normin"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("x"), JDoubleW("scale"), JDoubleArray("cnorm"), JIntW("info")),
    Routine   (             "dlatrz",   JInt("m"), JInt("n"), JInt("l"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work")),
    Routine   (             "dlatzm",   JString("side"), JInt("m"), JInt("n"), JDoubleArray("v"), JInt("incv"), JDouble("tau"), JDoubleArray("c1"), JDoubleArray("c2"), JInt("Ldc"), JDoubleArray("work")),
    Routine   (             "dlauu2",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "dlauum",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("info")),
    Routine_NI(             "dlazq3",   JInt("i0"), JIntW("n0"), JDoubleArray("z"), JInt("pp"), JDoubleW("dmin"), JDoubleW("sigma"), JDoubleW("desig"), JDoubleW("qmax"), JIntW("nfail"), JIntW("iter"), JIntW("ndiv"), JBoolean("ieee"), JIntW("ttype"), JDoubleW("dmin1"), JDoubleW("dmin2"), JDoubleW("dn"), JDoubleW("dn1"), JDoubleW("dn2"), JDoubleW("tau")),
    Routine_NI(             "dlazq4",   JInt("i0"), JInt("n0"), JDoubleArray("z"), JInt("pp"), JInt("n0in"), JDouble("dmin"), JDouble("dmin1"), JDouble("dmin2"), JDouble("dn"), JDouble("dn1"), JDouble("dn2"), JDoubleW("tau"), JIntW("ttype"), JDoubleW("g")),
    Routine   (             "dopgtr",   JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("tau"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dopmtr",   JString("side"), JString("uplo"), JString("trans"), JInt("m"), JInt("n"), JDoubleArray("ap"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dorg2l",   JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dorg2r",   JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dorgbr",   JString("vect"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dorghr",   JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dorgl2",   JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dorglq",   JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dorgql",   JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dorgqr",   JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dorgr2",   JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dorgrq",   JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dorgtr",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dorm2l",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dorm2r",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dormbr",   JString("vect"), JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dormhr",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dorml2",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dormlq",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dormql",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dormqr",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dormr2",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dormr3",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JInt("l"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dormrq",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dormrz",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JInt("l"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dormtr",   JString("side"), JString("uplo"), JString("trans"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dpbcon",   JString("uplo"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JDouble("anorm"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dpbequ",   JString("uplo"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("s"), JDoubleW("scond"), JDoubleW("amax"), JIntW("info")),
    Routine   (             "dpbrfs",   JString("uplo"), JInt("n"), JInt("kd"), JInt("nrhs"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("afb"), JInt("ldafb"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dpbstf",   JString("uplo"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JIntW("info")),
    Routine   (             "dpbsv",    JString("uplo"), JInt("n"), JInt("kd"), JInt("nrhs"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dpbsvx",   JString("fact"), JString("uplo"), JInt("n"), JInt("kd"), JInt("nrhs"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("afb"), JInt("ldafb"), JStringW("equed"), JDoubleArray("s"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleW("rcond"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dpbtf2",   JString("uplo"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JIntW("info")),
    Routine   (             "dpbtrf",   JString("uplo"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JIntW("info")),
    Routine   (             "dpbtrs",   JString("uplo"), JInt("n"), JInt("kd"), JInt("nrhs"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dpocon",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDouble("anorm"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dpoequ",   JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("s"), JDoubleW("scond"), JDoubleW("amax"), JIntW("info")),
    Routine   (             "dporfs",   JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("af"), JInt("ldaf"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dposv",    JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dposvx",   JString("fact"), JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("af"), JInt("ldaf"), JStringW("equed"), JDoubleArray("s"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleW("rcond"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dpotf2",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "dpotrf",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "dpotri",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "dpotrs",   JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dppcon",   JString("uplo"), JInt("n"), JDoubleArray("ap"), JDouble("anorm"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dppequ",   JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("s"), JDoubleW("scond"), JDoubleW("amax"), JIntW("info")),
    Routine   (             "dpprfs",   JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("ap"), JDoubleArray("afp"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dppsv",    JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("ap"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dppsvx",   JString("fact"), JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("ap"), JDoubleArray("afp"), JStringW("equed"), JDoubleArray("s"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleW("rcond"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dpptrf",   JString("uplo"), JInt("n"), JDoubleArray("ap"), JIntW("info")),
    Routine   (             "dpptri",   JString("uplo"), JInt("n"), JDoubleArray("ap"), JIntW("info")),
    Routine   (             "dpptrs",   JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("ap"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dptcon",   JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDouble("anorm"), JDoubleW("rcond"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dpteqr",   JString("compz"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dptrfs",   JInt("n"), JInt("nrhs"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("df"), JDoubleArray("ef"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dptsv",    JInt("n"), JInt("nrhs"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dptsvx",   JString("fact"), JInt("n"), JInt("nrhs"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("df"), JDoubleArray("ef"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleW("rcond"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dpttrf",   JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JIntW("info")),
    Routine   (             "dpttrs",   JInt("n"), JInt("nrhs"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dptts2",   JInt("n"), JInt("nrhs"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("b"), JInt("ldb")),
    Routine   (             "drscl",    JInt("n"), JDouble("sa"), JDoubleArray("sx"), JInt("incx")),
    Routine   (             "dsbev",    JString("jobz"), JString("uplo"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dsbevd",   JString("jobz"), JString("uplo"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dsbevx",   JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("q"), JInt("ldq"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "dsbgst",   JString("vect"), JString("uplo"), JInt("n"), JInt("ka"), JInt("kb"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("bb"), JInt("ldbb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dsbgv",    JString("jobz"), JString("uplo"), JInt("n"), JInt("ka"), JInt("kb"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("bb"), JInt("ldbb"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dsbgvd",   JString("jobz"), JString("uplo"), JInt("n"), JInt("ka"), JInt("kb"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("bb"), JInt("ldbb"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dsbgvx",   JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JInt("ka"), JInt("kb"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("bb"), JInt("ldbb"), JDoubleArray("q"), JInt("ldq"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "dsbtrd",   JString("vect"), JString("uplo"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dsgesv",   JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("work"), JFloatArray("swork"), JIntW("iter"), JIntW("info")),
    Routine   (             "dspcon",   JString("uplo"), JInt("n"), JDoubleArray("ap"), JIntArray("ipiv"), JDouble("anorm"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dspev",    JString("jobz"), JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dspevd",   JString("jobz"), JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dspevx",   JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JDoubleArray("ap"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "dspgst",   JInt("itype"), JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("bp"), JIntW("info")),
    Routine   (             "dspgv",    JInt("itype"), JString("jobz"), JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("bp"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dspgvd",   JInt("itype"), JString("jobz"), JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("bp"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dspgvx",   JInt("itype"), JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("bp"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "dsprfs",   JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("ap"), JDoubleArray("afp"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dspsv",    JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("ap"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dspsvx",   JString("fact"), JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("ap"), JDoubleArray("afp"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleW("rcond"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dsptrd",   JString("uplo"), JInt("n"), JDoubleArray("ap"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("tau"), JIntW("info")),
    Routine   (             "dsptrf",   JString("uplo"), JInt("n"), JDoubleArray("ap"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "dsptri",   JString("uplo"), JInt("n"), JDoubleArray("ap"), JIntArray("ipiv"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dsptrs",   JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("ap"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dstebz",   JString("range"), JString("order"), JInt("n"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JDoubleArray("d"), JDoubleArray("e"), JIntW("m"), JIntW("nsplit"), JDoubleArray("w"), JIntArray("iblock"), JIntArray("isplit"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dstedc",   JString("compz"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dstegr",   JString("jobz"), JString("range"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JIntArray("isuppz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dstein",   JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JInt("m"), JDoubleArray("w"), JIntArray("iblock"), JIntArray("isplit"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "dstemr",   JString("jobz"), JString("range"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JInt("nzc"), JIntArray("isuppz"), JBooleanW("tryrac"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dsteqr",   JString("compz"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dsterf",   JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JIntW("info")),
    Routine   (             "dstev",    JString("jobz"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dstevd",   JString("jobz"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dstevr",   JString("jobz"), JString("range"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JIntArray("isuppz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dstevx",   JString("jobz"), JString("range"), JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "dsycon",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JDouble("anorm"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dsyev",    JString("jobz"), JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("w"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dsyevd",   JString("jobz"), JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("w"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dsyevr",   JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JIntArray("isuppz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dsyevx",   JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "dsygs2",   JInt("itype"), JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dsygst",   JInt("itype"), JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dsygv",    JInt("itype"), JString("jobz"), JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("w"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dsygvd",   JInt("itype"), JString("jobz"), JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("w"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dsygvx",   JInt("itype"), JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDouble("vl"), JDouble("vu"), JInt("il"), JInt("iu"), JDouble("abstol"), JIntW("m"), JDoubleArray("w"), JDoubleArray("z"), JInt("ldz"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "dsyrfs",   JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("af"), JInt("ldaf"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dsysv",    JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dsysvx",   JString("fact"), JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("af"), JInt("ldaf"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleW("rcond"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dsytd2",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("tau"), JIntW("info")),
    Routine   (             "dsytf2",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "dsytrd",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dsytrf",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dsytri",   JString("uplo"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dsytrs",   JString("uplo"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JIntArray("ipiv"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dtbcon",   JString("norm"), JString("uplo"), JString("diag"), JInt("n"), JInt("kd"), JDoubleArray("ab"), JInt("ldab"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dtbrfs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("kd"), JInt("nrhs"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dtbtrs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("kd"), JInt("nrhs"), JDoubleArray("ab"), JInt("ldab"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dtgevc",   JString("side"), JString("howmny"), JBooleanArray("select"), JInt("n"), JDoubleArray("s"), JInt("lds"), JDoubleArray("p"), JInt("ldp"), JDoubleArray("vl"), JInt("ldvl"), JDoubleArray("vr"), JInt("ldvr"), JInt("mm"), JIntW("m"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dtgex2",   JBoolean("wantq"), JBoolean("wantz"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("z"), JInt("ldz"), JInt("j1"), JInt("n1"), JInt("n2"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dtgexc",   JBoolean("wantq"), JBoolean("wantz"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("z"), JInt("ldz"), JIntW("ifst"), JIntW("ilst"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "dtgsen",   JInt("ijob"), JBoolean("wantq"), JBoolean("wantz"), JBooleanArray("select"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("alphar"), JDoubleArray("alphai"), JDoubleArray("beta"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("z"), JInt("ldz"), JIntW("m"), JDoubleW("pl"), JDoubleW("pr"), JDoubleArray("dif"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dtgsja",   JString("jobu"), JString("jobv"), JString("jobq"), JInt("m"), JInt("p"), JInt("n"), JInt("k"), JInt("l"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDouble("tola"), JDouble("tolb"), JDoubleArray("alpha"), JDoubleArray("beta"), JDoubleArray("u"), JInt("ldu"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("work"), JIntW("ncycle"), JIntW("info")),
    Routine   (             "dtgsna",   JString("job"), JString("howmny"), JBooleanArray("select"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("vl"), JInt("ldvl"), JDoubleArray("vr"), JInt("ldvr"), JDoubleArray("s"), JDoubleArray("dif"), JInt("mm"), JIntW("m"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dtgsy2",   JString("trans"), JInt("ijob"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("d"), JInt("ldd"), JDoubleArray("e"), JInt("lde"), JDoubleArray("f"), JInt("ldf"), JDoubleW("scale"), JDoubleW("rdsum"), JDoubleW("rdscal"), JIntArray("iwork"), JIntW("pq"), JIntW("info")),
    Routine   (             "dtgsyl",   JString("trans"), JInt("ijob"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("c"), JInt("Ldc"), JDoubleArray("d"), JInt("ldd"), JDoubleArray("e"), JInt("lde"), JDoubleArray("f"), JInt("ldf"), JDoubleW("scale"), JDoubleW("dif"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dtpcon",   JString("norm"), JString("uplo"), JString("diag"), JInt("n"), JDoubleArray("ap"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dtprfs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("nrhs"), JDoubleArray("ap"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dtptri",   JString("uplo"), JString("diag"), JInt("n"), JDoubleArray("ap"), JIntW("info")),
    Routine   (             "dtptrs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("nrhs"), JDoubleArray("ap"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dtrcon",   JString("norm"), JString("uplo"), JString("diag"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleW("rcond"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dtrevc",   JString("side"), JString("howmny"), JBooleanArray("select"), JInt("n"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("vl"), JInt("ldvl"), JDoubleArray("vr"), JInt("ldvr"), JInt("mm"), JIntW("m"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dtrexc",   JString("compq"), JInt("n"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("q"), JInt("ldq"), JIntW("ifst"), JIntW("ilst"), JDoubleArray("work"), JIntW("info")),
    Routine   (             "dtrrfs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("x"), JInt("ldx"), JDoubleArray("ferr"), JDoubleArray("berr"), JDoubleArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dtrsen",   JString("job"), JString("compq"), JBooleanArray("select"), JInt("n"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("wr"), JDoubleArray("wi"), JIntW("m"), JDoubleW("s"), JDoubleW("sep"), JDoubleArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "dtrsna",   JString("job"), JString("howmny"), JBooleanArray("select"), JInt("n"), JDoubleArray("t"), JInt("ldt"), JDoubleArray("vl"), JInt("ldvl"), JDoubleArray("vr"), JInt("ldvr"), JDoubleArray("s"), JDoubleArray("sep"), JInt("mm"), JIntW("m"), JDoubleArray("work"), JInt("ldwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "dtrsyl",   JString("trana"), JString("tranb"), JInt("isgn"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JDoubleArray("c"), JInt("Ldc"), JDoubleW("scale"), JIntW("info")),
    Routine   (             "dtrti2",   JString("uplo"), JString("diag"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "dtrtri",   JString("uplo"), JString("diag"), JInt("n"), JDoubleArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "dtrtrs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("nrhs"), JDoubleArray("a"), JInt("lda"), JDoubleArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "dtzrqf",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JIntW("info")),
    Routine   (             "dtzrzf",   JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JDoubleArray("tau"), JDoubleArray("work"), JInt("lwork"), JIntW("info")),
    RoutineR  (JIntR(),     "ieeeck",   JInt("ispec"), JFloat("zero"), JFloat("one")),
    RoutineR  (JIntR(),     "ilaenv",   JInt("ispec"), JString("name"), JString("opts"), JInt("n1"), JInt("n2"), JInt("n3"), JInt("n4")),
    Routine   (             "ilaver",   JIntW("vers_major"), JIntW("vers_minor"), JIntW("vers_patch")),
    RoutineR  (JIntR(),     "iparmq",   JInt("ispec"), JString("name"), JString("opts"), JInt("n"), JInt("ilo"), JInt("ihi"), JInt("lwork")),
    RoutineR  (JBooleanR(), "lsamen",   JInt("n"), JString("ca"), JString("cb")),
    Routine   (             "sbdsdc",   JString("uplo"), JString("compq"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloatArray("u"), JInt("ldu"), JFloatArray("vt"), JInt("ldvt"), JFloatArray("q"), JIntArray("iq"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sbdsqr",   JString("uplo"), JInt("n"), JInt("ncvt"), JInt("nru"), JInt("ncc"), JFloatArray("d"), JFloatArray("e"), JFloatArray("vt"), JInt("ldvt"), JFloatArray("u"), JInt("ldu"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sdisna",   JString("job"), JInt("m"), JInt("n"), JFloatArray("d"), JFloatArray("sep"), JIntW("info")),
    Routine   (             "sgbbrd",   JString("vect"), JInt("m"), JInt("n"), JInt("ncc"), JInt("kl"), JInt("ku"), JFloatArray("ab"), JInt("ldab"), JFloatArray("d"), JFloatArray("e"), JFloatArray("q"), JInt("ldq"), JFloatArray("pt"), JInt("ldpt"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sgbcon",   JString("norm"), JInt("n"), JInt("kl"), JInt("ku"), JFloatArray("ab"), JInt("ldab"), JIntArray("ipiv"), JFloat("anorm"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgbequ",   JInt("m"), JInt("n"), JInt("kl"), JInt("ku"), JFloatArray("ab"), JInt("ldab"), JFloatArray("r"), JFloatArray("c"), JFloatW("rowcnd"), JFloatW("colcnd"), JFloatW("amax"), JIntW("info")),
    Routine   (             "sgbrfs",   JString("trans"), JInt("n"), JInt("kl"), JInt("ku"), JInt("nrhs"), JFloatArray("ab"), JInt("ldab"), JFloatArray("afb"), JInt("ldafb"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgbsv",    JInt("n"), JInt("kl"), JInt("ku"), JInt("nrhs"), JFloatArray("ab"), JInt("ldab"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sgbsvx",   JString("fact"), JString("trans"), JInt("n"), JInt("kl"), JInt("ku"), JInt("nrhs"), JFloatArray("ab"), JInt("ldab"), JFloatArray("afb"), JInt("ldafb"), JIntArray("ipiv"), JStringW("equed"), JFloatArray("r"), JFloatArray("c"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatW("rcond"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgbtf2",   JInt("m"), JInt("n"), JInt("kl"), JInt("ku"), JFloatArray("ab"), JInt("ldab"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "sgbtrf",   JInt("m"), JInt("n"), JInt("kl"), JInt("ku"), JFloatArray("ab"), JInt("ldab"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "sgbtrs",   JString("trans"), JInt("n"), JInt("kl"), JInt("ku"), JInt("nrhs"), JFloatArray("ab"), JInt("ldab"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sgebak",   JString("job"), JString("side"), JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("scale"), JInt("m"), JFloatArray("v"), JInt("ldv"), JIntW("info")),
    Routine   (             "sgebal",   JString("job"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("ilo"), JIntW("ihi"), JFloatArray("scale"), JIntW("info")),
    Routine   (             "sgebd2",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("d"), JFloatArray("e"), JFloatArray("tauq"), JFloatArray("taup"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sgebrd",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("d"), JFloatArray("e"), JFloatArray("tauq"), JFloatArray("taup"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgecon",   JString("norm"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloat("anorm"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgeequ",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("r"), JFloatArray("c"), JFloatW("rowcnd"), JFloatW("colcnd"), JFloatW("amax"), JIntW("info")),
    Routine_NI(             "sgees",    JString("jobvs"), JString("sort"), JObject("select"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("sdim"), JFloatArray("wr"), JFloatArray("wi"), JFloatArray("vs"), JInt("ldvs"), JFloatArray("work"), JInt("lwork"), JBooleanArray("bwork"), JIntW("info")),
    Routine_NI(             "sgeesx",   JString("jobvs"), JString("sort"), JObject("select"), JString("sense"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("sdim"), JFloatArray("wr"), JFloatArray("wi"), JFloatArray("vs"), JInt("ldvs"), JFloatW("rconde"), JFloatW("rcondv"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JBooleanArray("bwork"), JIntW("info")),
    Routine   (             "sgeev",    JString("jobvl"), JString("jobvr"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("wr"), JFloatArray("wi"), JFloatArray("vl"), JInt("ldvl"), JFloatArray("vr"), JInt("ldvr"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgeevx",   JString("balanc"), JString("jobvl"), JString("jobvr"), JString("sense"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("wr"), JFloatArray("wi"), JFloatArray("vl"), JInt("ldvl"), JFloatArray("vr"), JInt("ldvr"), JIntW("ilo"), JIntW("ihi"), JFloatArray("scale"), JFloatW("abnrm"), JFloatArray("rconde"), JFloatArray("rcondv"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgegs",    JString("jobvsl"), JString("jobvsr"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("alphar"), JFloatArray("alphai"), JFloatArray("beta"), JFloatArray("vsl"), JInt("ldvsl"), JFloatArray("vsr"), JInt("ldvsr"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgegv",    JString("jobvl"), JString("jobvr"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("alphar"), JFloatArray("alphai"), JFloatArray("beta"), JFloatArray("vl"), JInt("ldvl"), JFloatArray("vr"), JInt("ldvr"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgehd2",   JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sgehrd",   JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgelq2",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sgelqf",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgels",    JString("trans"), JInt("m"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgelsd",   JInt("m"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("s"), JFloat("rcond"), JIntW("rank"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgelss",   JInt("m"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("s"), JFloat("rcond"), JIntW("rank"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgelsx",   JInt("m"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JIntArray("jpvt"), JFloat("rcond"), JIntW("rank"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sgelsy",   JInt("m"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JIntArray("jpvt"), JFloat("rcond"), JIntW("rank"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgeql2",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sgeqlf",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgeqp3",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntArray("jpvt"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgeqpf",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntArray("jpvt"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sgeqr2",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sgeqrf",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgerfs",   JString("trans"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("af"), JInt("ldaf"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgerq2",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sgerqf",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgesc2",   JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("rhs"), JIntArray("ipiv"), JIntArray("jpiv"), JFloatW("scale")),
    Routine   (             "sgesdd",   JString("jobz"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("s"), JFloatArray("u"), JInt("ldu"), JFloatArray("vt"), JInt("ldvt"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgesv",    JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sgesvd",   JString("jobu"), JString("jobvt"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("s"), JFloatArray("u"), JInt("ldu"), JFloatArray("vt"), JInt("ldvt"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgesvx",   JString("fact"), JString("trans"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("af"), JInt("ldaf"), JIntArray("ipiv"), JStringW("equed"), JFloatArray("r"), JFloatArray("c"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatW("rcond"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgetc2",   JInt("n"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JIntArray("jpiv"), JIntW("info")),
    Routine   (             "sgetf2",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "sgetrf",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "sgetri",   JInt("n"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgetrs",   JString("trans"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sggbak",   JString("job"), JString("side"), JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("lscale"), JFloatArray("rscale"), JInt("m"), JFloatArray("v"), JInt("ldv"), JIntW("info")),
    Routine   (             "sggbal",   JString("job"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JIntW("ilo"), JIntW("ihi"), JFloatArray("lscale"), JFloatArray("rscale"), JFloatArray("work"), JIntW("info")),
    Routine_NI(             "sgges",    JString("jobvsl"), JString("jobvsr"), JString("sort"), JObject("selctg"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JIntW("sdim"), JFloatArray("alphar"), JFloatArray("alphai"), JFloatArray("beta"), JFloatArray("vsl"), JInt("ldvsl"), JFloatArray("vsr"), JInt("ldvsr"), JFloatArray("work"), JInt("lwork"), JBooleanArray("bwork"), JIntW("info")),
    Routine_NI(             "sggesx",   JString("jobvsl"), JString("jobvsr"), JString("sort"), JObject("selctg"), JString("sense"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JIntW("sdim"), JFloatArray("alphar"), JFloatArray("alphai"), JFloatArray("beta"), JFloatArray("vsl"), JInt("ldvsl"), JFloatArray("vsr"), JInt("ldvsr"), JFloatArray("rconde"), JFloatArray("rcondv"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JBooleanArray("bwork"), JIntW("info")),
    Routine   (             "sggev",    JString("jobvl"), JString("jobvr"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("alphar"), JFloatArray("alphai"), JFloatArray("beta"), JFloatArray("vl"), JInt("ldvl"), JFloatArray("vr"), JInt("ldvr"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sggevx",   JString("balanc"), JString("jobvl"), JString("jobvr"), JString("sense"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("alphar"), JFloatArray("alphai"), JFloatArray("beta"), JFloatArray("vl"), JInt("ldvl"), JFloatArray("vr"), JInt("ldvr"), JIntW("ilo"), JIntW("ihi"), JFloatArray("lscale"), JFloatArray("rscale"), JFloatW("abnrm"), JFloatW("bbnrm"), JFloatArray("rconde"), JFloatArray("rcondv"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JBooleanArray("bwork"), JIntW("info")),
    Routine   (             "sggglm",   JInt("n"), JInt("m"), JInt("p"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("d"), JFloatArray("x"), JFloatArray("y"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sgghrd",   JString("compq"), JString("compz"), JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("q"), JInt("ldq"), JFloatArray("z"), JInt("ldz"), JIntW("info")),
    Routine   (             "sgglse",   JInt("m"), JInt("n"), JInt("p"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("c"), JFloatArray("d"), JFloatArray("x"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sggqrf",   JInt("n"), JInt("m"), JInt("p"), JFloatArray("a"), JInt("lda"), JFloatArray("taua"), JFloatArray("b"), JInt("ldb"), JFloatArray("taub"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sggrqf",   JInt("m"), JInt("p"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("taua"), JFloatArray("b"), JInt("ldb"), JFloatArray("taub"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sggsvd",   JString("jobu"), JString("jobv"), JString("jobq"), JInt("m"), JInt("n"), JInt("p"), JIntW("k"), JIntW("l"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("alpha"), JFloatArray("beta"), JFloatArray("u"), JInt("ldu"), JFloatArray("v"), JInt("ldv"), JFloatArray("q"), JInt("ldq"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sggsvp",   JString("jobu"), JString("jobv"), JString("jobq"), JInt("m"), JInt("p"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloat("tola"), JFloat("tolb"), JIntW("k"), JIntW("l"), JFloatArray("u"), JInt("ldu"), JFloatArray("v"), JInt("ldv"), JFloatArray("q"), JInt("ldq"), JIntArray("iwork"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sgtcon",   JString("norm"), JInt("n"), JFloatArray("dl"), JFloatArray("d"), JFloatArray("du"), JFloatArray("du2"), JIntArray("ipiv"), JFloat("anorm"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgtrfs",   JString("trans"), JInt("n"), JInt("nrhs"), JFloatArray("dl"), JFloatArray("d"), JFloatArray("du"), JFloatArray("dlf"), JFloatArray("df"), JFloatArray("duf"), JFloatArray("du2"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgtsv",    JInt("n"), JInt("nrhs"), JFloatArray("dl"), JFloatArray("d"), JFloatArray("du"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sgtsvx",   JString("fact"), JString("trans"), JInt("n"), JInt("nrhs"), JFloatArray("dl"), JFloatArray("d"), JFloatArray("du"), JFloatArray("dlf"), JFloatArray("df"), JFloatArray("duf"), JFloatArray("du2"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatW("rcond"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sgttrf",   JInt("n"), JFloatArray("dl"), JFloatArray("d"), JFloatArray("du"), JFloatArray("du2"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "sgttrs",   JString("trans"), JInt("n"), JInt("nrhs"), JFloatArray("dl"), JFloatArray("d"), JFloatArray("du"), JFloatArray("du2"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sgtts2",   JInt("itrans"), JInt("n"), JInt("nrhs"), JFloatArray("dl"), JFloatArray("d"), JFloatArray("du"), JFloatArray("du2"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb")),
    Routine   (             "shgeqz",   JString("job"), JString("compq"), JString("compz"), JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("h"), JInt("ldh"), JFloatArray("t"), JInt("ldt"), JFloatArray("alphar"), JFloatArray("alphai"), JFloatArray("beta"), JFloatArray("q"), JInt("ldq"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "shsein",   JString("side"), JString("eigsrc"), JString("initv"), JBooleanArray("select"), JInt("n"), JFloatArray("h"), JInt("ldh"), JFloatArray("wr"), JFloatArray("wi"), JFloatArray("vl"), JInt("ldvl"), JFloatArray("vr"), JInt("ldvr"), JInt("mm"), JIntW("m"), JFloatArray("work"), JIntArray("ifaill"), JIntArray("ifailr"), JIntW("info")),
    Routine   (             "shseqr",   JString("job"), JString("compz"), JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("h"), JInt("ldh"), JFloatArray("wr"), JFloatArray("wi"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    RoutineR  (JBooleanR(), "sisnan",   JFloat("sin")),
    Routine   (             "slabad",   JFloatW("small"), JFloatW("large")),
    Routine   (             "slabrd",   JInt("m"), JInt("n"), JInt("nb"), JFloatArray("a"), JInt("lda"), JFloatArray("d"), JFloatArray("e"), JFloatArray("tauq"), JFloatArray("taup"), JFloatArray("x"), JInt("ldx"), JFloatArray("y"), JInt("ldy")),
    Routine   (             "slacn2",   JInt("n"), JFloatArray("v"), JFloatArray("x"), JIntArray("isgn"), JFloatW("est"), JIntW("kase"), JIntArray("isave")),
    Routine   (             "slacon",   JInt("n"), JFloatArray("v"), JFloatArray("x"), JIntArray("isgn"), JFloatW("est"), JIntW("kase")),
    Routine   (             "slacpy",   JString("uplo"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb")),
    Routine   (             "sladiv",   JFloat("a"), JFloat("b"), JFloat("c"), JFloat("d"), JFloatW("p"), JFloatW("q")),
    Routine   (             "slae2",    JFloat("a"), JFloat("b"), JFloat("c"), JFloatW("rt1"), JFloatW("rt2")),
    Routine   (             "slaebz",   JInt("ijob"), JInt("nitmax"), JInt("n"), JInt("mmax"), JInt("minp"), JInt("nbmin"), JFloat("abstol"), JFloat("reltol"), JFloat("pivmin"), JFloatArray("d"), JFloatArray("e"), JFloatArray("e2"), JIntArray("nval"), JFloatArray("ab"), JFloatArray("c"), JIntW("mout"), JIntArray("nab"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slaed0",   JInt("icompq"), JInt("qsiz"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloatArray("q"), JInt("ldq"), JFloatArray("qstore"), JInt("ldqs"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slaed1",   JInt("n"), JFloatArray("d"), JFloatArray("q"), JInt("ldq"), JIntArray("indxq"), JFloatW("rho"), JInt("cutpnt"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slaed2",   JIntW("k"), JInt("n"), JInt("n1"), JFloatArray("d"), JFloatArray("q"), JInt("ldq"), JIntArray("indxq"), JFloatW("rho"), JFloatArray("z"), JFloatArray("dlamda"), JFloatArray("w"), JFloatArray("q2"), JIntArray("indx"), JIntArray("indxc"), JIntArray("indxp"), JIntArray("coltyp"), JIntW("info")),
    Routine   (             "slaed3",   JInt("k"), JInt("n"), JInt("n1"), JFloatArray("d"), JFloatArray("q"), JInt("ldq"), JFloat("rho"), JFloatArray("dlamda"), JFloatArray("q2"), JIntArray("indx"), JIntArray("ctot"), JFloatArray("w"), JFloatArray("s"), JIntW("info")),
    Routine   (             "slaed4",   JInt("n"), JInt("i"), JFloatArray("d"), JFloatArray("z"), JFloatArray("delta"), JFloat("rho"), JFloatW("dlam"), JIntW("info")),
    Routine   (             "slaed5",   JInt("i"), JFloatArray("d"), JFloatArray("z"), JFloatArray("delta"), JFloat("rho"), JFloatW("dlam")),
    Routine   (             "slaed6",   JInt("kniter"), JBoolean("orgati"), JFloat("rho"), JFloatArray("d"), JFloatArray("z"), JFloat("finit"), JFloatW("tau"), JIntW("info")),
    Routine   (             "slaed7",   JInt("icompq"), JInt("n"), JInt("qsiz"), JInt("tlvls"), JInt("curlvl"), JInt("curpbm"), JFloatArray("d"), JFloatArray("q"), JInt("ldq"), JIntArray("indxq"), JFloatW("rho"), JInt("cutpnt"), JFloatArray("qstore"), JIntArray("qptr"), JIntArray("prmptr"), JIntArray("perm"), JIntArray("givptr"), JIntArray("givcol"), JFloatArray("givnum"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slaed8",   JInt("icompq"), JIntW("k"), JInt("n"), JInt("qsiz"), JFloatArray("d"), JFloatArray("q"), JInt("ldq"), JIntArray("indxq"), JFloatW("rho"), JInt("cutpnt"), JFloatArray("z"), JFloatArray("dlamda"), JFloatArray("q2"), JInt("ldq2"), JFloatArray("w"), JIntArray("perm"), JIntW("givptr"), JIntArray("givcol"), JFloatArray("givnum"), JIntArray("indxp"), JIntArray("indx"), JIntW("info")),
    Routine   (             "slaed9",   JInt("k"), JInt("kstart"), JInt("kstop"), JInt("n"), JFloatArray("d"), JFloatArray("q"), JInt("ldq"), JFloat("rho"), JFloatArray("dlamda"), JFloatArray("w"), JFloatArray("s"), JInt("lds"), JIntW("info")),
    Routine   (             "slaeda",   JInt("n"), JInt("tlvls"), JInt("curlvl"), JInt("curpbm"), JIntArray("prmptr"), JIntArray("perm"), JIntArray("givptr"), JIntArray("givcol"), JFloatArray("givnum"), JFloatArray("q"), JIntArray("qptr"), JFloatArray("z"), JFloatArray("ztemp"), JIntW("info")),
    Routine   (             "slaein",   JBoolean("rightv"), JBoolean("noinit"), JInt("n"), JFloatArray("h"), JInt("ldh"), JFloat("wr"), JFloat("wi"), JFloatArray("vr"), JFloatArray("vi"), JFloatArray("b"), JInt("ldb"), JFloatArray("work"), JFloat("eps3"), JFloat("smlnum"), JFloat("bignum"), JIntW("info")),
    Routine   (             "slaev2",   JFloat("a"), JFloat("b"), JFloat("c"), JFloatW("rt1"), JFloatW("rt2"), JFloatW("cs1"), JFloatW("sn1")),
    Routine   (             "slaexc",   JBoolean("wantq"), JInt("n"), JFloatArray("t"), JInt("ldt"), JFloatArray("q"), JInt("ldq"), JInt("j1"), JInt("n1"), JInt("n2"), JFloatArray("work"), JIntW("info")),
    Routine   (             "slag2",    JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloat("safmin"), JFloatW("scale1"), JFloatW("scale2"), JFloatW("wr1"), JFloatW("wr2"), JFloatW("wi")),
    Routine   (             "slag2d",   JInt("m"), JInt("n"), JFloatArray("sa"), JInt("ldsa"), JDoubleArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "slags2",   JBoolean("upper"), JFloat("a1"), JFloat("a2"), JFloat("a3"), JFloat("b1"), JFloat("b2"), JFloat("b3"), JFloatW("csu"), JFloatW("snu"), JFloatW("csv"), JFloatW("snv"), JFloatW("csq"), JFloatW("snq")),
    Routine   (             "slagtf",   JInt("n"), JFloatArray("a"), JFloat("lambda"), JFloatArray("b"), JFloatArray("c"), JFloat("tol"), JFloatArray("d"), JIntArray("in"), JIntW("info")),
    Routine   (             "slagtm",   JString("trans"), JInt("n"), JInt("nrhs"), JFloat("alpha"), JFloatArray("dl"), JFloatArray("d"), JFloatArray("du"), JFloatArray("x"), JInt("ldx"), JFloat("beta"), JFloatArray("b"), JInt("ldb")),
    Routine   (             "slagts",   JInt("job"), JInt("n"), JFloatArray("a"), JFloatArray("b"), JFloatArray("c"), JFloatArray("d"), JIntArray("in"), JFloatArray("y"), JFloatW("tol"), JIntW("info")),
    Routine   (             "slagv2",   JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("alphar"), JFloatArray("alphai"), JFloatArray("beta"), JFloatW("csl"), JFloatW("snl"), JFloatW("csr"), JFloatW("snr")),
    Routine   (             "slahqr",   JBoolean("wantt"), JBoolean("wantz"), JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("h"), JInt("ldh"), JFloatArray("wr"), JFloatArray("wi"), JInt("iloz"), JInt("ihiz"), JFloatArray("z"), JInt("ldz"), JIntW("info")),
    Routine   (             "slahr2",   JInt("n"), JInt("k"), JInt("nb"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("t"), JInt("ldt"), JFloatArray("y"), JInt("ldy")),
    Routine   (             "slahrd",   JInt("n"), JInt("k"), JInt("nb"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("t"), JInt("ldt"), JFloatArray("y"), JInt("ldy")),
    Routine   (             "slaic1",   JInt("job"), JInt("j"), JFloatArray("x"), JFloat("sest"), JFloatArray("w"), JFloat("gamma"), JFloatW("sestpr"), JFloatW("s"), JFloatW("c")),
    RoutineR  (JBooleanR(), "slaisnan", JFloat("sin1"), JFloat("sin2")),
    Routine   (             "slaln2",   JBoolean("ltrans"), JInt("na"), JInt("nw"), JFloat("smin"), JFloat("ca"), JFloatArray("a"), JInt("lda"), JFloat("d1"), JFloat("d2"), JFloatArray("b"), JInt("ldb"), JFloat("wr"), JFloat("wi"), JFloatArray("x"), JInt("ldx"), JFloatW("scale"), JFloatW("xnorm"), JIntW("info")),
    Routine   (             "slals0",   JInt("icompq"), JInt("nl"), JInt("nr"), JInt("sqre"), JInt("nrhs"), JFloatArray("b"), JInt("ldb"), JFloatArray("bx"), JInt("ldbx"), JIntArray("perm"), JInt("givptr"), JIntArray("givcol"), JInt("ldgcol"), JFloatArray("givnum"), JInt("ldgnum"), JFloatArray("poles"), JFloatArray("difl"), JFloatArray("difr"), JFloatArray("z"), JInt("k"), JFloat("c"), JFloat("s"), JFloatArray("work"), JIntW("info")),
    Routine   (             "slalsa",   JInt("icompq"), JInt("smlsiz"), JInt("n"), JInt("nrhs"), JFloatArray("b"), JInt("ldb"), JFloatArray("bx"), JInt("ldbx"), JFloatArray("u"), JInt("ldu"), JFloatArray("vt"), JIntArray("k"), JFloatArray("difl"), JFloatArray("difr"), JFloatArray("z"), JFloatArray("poles"), JIntArray("givptr"), JIntArray("givcol"), JInt("ldgcol"), JIntArray("perm"), JFloatArray("givnum"), JFloatArray("c"), JFloatArray("s"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slalsd",   JString("uplo"), JInt("smlsiz"), JInt("n"), JInt("nrhs"), JFloatArray("d"), JFloatArray("e"), JFloatArray("b"), JInt("ldb"), JFloat("rcond"), JIntW("rank"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slamrg",   JInt("n1"), JInt("n2"), JFloatArray("a"), JInt("strd1"), JInt("strd2"), JIntArray("index")),
    RoutineR  (JIntR(),     "slaneg",   JInt("n"), JFloatArray("d"), JFloatArray("lld"), JFloat("sigma"), JFloat("pivmin"), JInt("r")),
    RoutineR  (JFloatR(),   "slangb",   JString("norm"), JInt("n"), JInt("kl"), JInt("ku"), JFloatArray("ab"), JInt("ldab"), JFloatArray("work")),
    RoutineR  (JFloatR(),   "slange",   JString("norm"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("work")),
    RoutineR  (JFloatR(),   "slangt",   JString("norm"), JInt("n"), JFloatArray("dl"), JFloatArray("d"), JFloatArray("du")),
    RoutineR  (JFloatR(),   "slanhs",   JString("norm"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("work")),
    RoutineR  (JFloatR(),   "slansb",   JString("norm"), JString("uplo"), JInt("n"), JInt("k"), JFloatArray("ab"), JInt("ldab"), JFloatArray("work")),
    RoutineR  (JFloatR(),   "slansp",   JString("norm"), JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("work")),
    RoutineR  (JFloatR(),   "slanst",   JString("norm"), JInt("n"), JFloatArray("d"), JFloatArray("e")),
    RoutineR  (JFloatR(),   "slansy",   JString("norm"), JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("work")),
    RoutineR  (JFloatR(),   "slantb",   JString("norm"), JString("uplo"), JString("diag"), JInt("n"), JInt("k"), JFloatArray("ab"), JInt("ldab"), JFloatArray("work")),
    RoutineR  (JFloatR(),   "slantp",   JString("norm"), JString("uplo"), JString("diag"), JInt("n"), JFloatArray("ap"), JFloatArray("work")),
    RoutineR  (JFloatR(),   "slantr",   JString("norm"), JString("uplo"), JString("diag"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("work")),
    Routine   (             "slanv2",   JFloatW("a"), JFloatW("b"), JFloatW("c"), JFloatW("d"), JFloatW("rt1r"), JFloatW("rt1i"), JFloatW("rt2r"), JFloatW("rt2i"), JFloatW("cs"), JFloatW("sn")),
    Routine   (             "slapll",   JInt("n"), JFloatArray("x"), JInt("incx"), JFloatArray("y"), JInt("incy"), JFloatW("ssmin")),
    Routine   (             "slapmt",   JBoolean("forwrd"), JInt("m"), JInt("n"), JFloatArray("x"), JInt("ldx"), JIntArray("k")),
    RoutineR  (JFloatR(),   "slapy2",   JFloat("x"), JFloat("y")),
    RoutineR  (JFloatR(),   "slapy3",   JFloat("x"), JFloat("y"), JFloat("z")),
    Routine   (             "slaqgb",   JInt("m"), JInt("n"), JInt("kl"), JInt("ku"), JFloatArray("ab"), JInt("ldab"), JFloatArray("r"), JFloatArray("c"), JFloat("rowcnd"), JFloat("colcnd"), JFloat("amax"), JStringW("equed")),
    Routine   (             "slaqge",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("r"), JFloatArray("c"), JFloat("rowcnd"), JFloat("colcnd"), JFloat("amax"), JStringW("equed")),
    Routine   (             "slaqp2",   JInt("m"), JInt("n"), JInt("offset"), JFloatArray("a"), JInt("lda"), JIntArray("jpvt"), JFloatArray("tau"), JFloatArray("vn1"), JFloatArray("vn2"), JFloatArray("work")),
    Routine   (             "slaqps",   JInt("m"), JInt("n"), JInt("offset"), JInt("nb"), JIntW("kb"), JFloatArray("a"), JInt("lda"), JIntArray("jpvt"), JFloatArray("tau"), JFloatArray("vn1"), JFloatArray("vn2"), JFloatArray("auxv"), JFloatArray("f"), JInt("ldf")),
    Routine   (             "slaqr0",   JBoolean("wantt"), JBoolean("wantz"), JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("h"), JInt("ldh"), JFloatArray("wr"), JFloatArray("wi"), JInt("iloz"), JInt("ihiz"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "slaqr1",   JInt("n"), JFloatArray("h"), JInt("ldh"), JFloat("sr1"), JFloat("si1"), JFloat("sr2"), JFloat("si2"), JFloatArray("v")),
    Routine   (             "slaqr2",   JBoolean("wantt"), JBoolean("wantz"), JInt("n"), JInt("ktop"), JInt("kbot"), JInt("nw"), JFloatArray("h"), JInt("ldh"), JInt("iloz"), JInt("ihiz"), JFloatArray("z"), JInt("ldz"), JIntW("ns"), JIntW("nd"), JFloatArray("sr"), JFloatArray("si"), JFloatArray("v"), JInt("ldv"), JInt("nh"), JFloatArray("t"), JInt("ldt"), JInt("nv"), JFloatArray("wv"), JInt("ldwv"), JFloatArray("work"), JInt("lwork")),
    Routine   (             "slaqr3",   JBoolean("wantt"), JBoolean("wantz"), JInt("n"), JInt("ktop"), JInt("kbot"), JInt("nw"), JFloatArray("h"), JInt("ldh"), JInt("iloz"), JInt("ihiz"), JFloatArray("z"), JInt("ldz"), JIntW("ns"), JIntW("nd"), JFloatArray("sr"), JFloatArray("si"), JFloatArray("v"), JInt("ldv"), JInt("nh"), JFloatArray("t"), JInt("ldt"), JInt("nv"), JFloatArray("wv"), JInt("ldwv"), JFloatArray("work"), JInt("lwork")),
    Routine   (             "slaqr4",   JBoolean("wantt"), JBoolean("wantz"), JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("h"), JInt("ldh"), JFloatArray("wr"), JFloatArray("wi"), JInt("iloz"), JInt("ihiz"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "slaqr5",   JBoolean("wantt"), JBoolean("wantz"), JInt("kacc22"), JInt("n"), JInt("ktop"), JInt("kbot"), JInt("nshfts"), JFloatArray("sr"), JFloatArray("si"), JFloatArray("h"), JInt("ldh"), JInt("iloz"), JInt("ihiz"), JFloatArray("z"), JInt("ldz"), JFloatArray("v"), JInt("ldv"), JFloatArray("u"), JInt("ldu"), JInt("nv"), JFloatArray("wv"), JInt("ldwv"), JInt("nh"), JFloatArray("wh"), JInt("ldwh")),
    Routine   (             "slaqsb",   JString("uplo"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JFloatArray("s"), JFloat("scond"), JFloat("amax"), JStringW("equed")),
    Routine   (             "slaqsp",   JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("s"), JFloat("scond"), JFloat("amax"), JStringW("equed")),
    Routine   (             "slaqsy",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("s"), JFloat("scond"), JFloat("amax"), JStringW("equed")),
    Routine   (             "slaqtr",   JBoolean("ltran"), JBoolean("lreal"), JInt("n"), JFloatArray("t"), JInt("ldt"), JFloatArray("b"), JFloat("w"), JFloatW("scale"), JFloatArray("x"), JFloatArray("work"), JIntW("info")),
    Routine   (             "slar1v",   JInt("n"), JInt("b1"), JInt("bn"), JFloat("lambda"), JFloatArray("d"), JFloatArray("l"), JFloatArray("ld"), JFloatArray("lld"), JFloat("pivmin"), JFloat("gaptol"), JFloatArray("z"), JBoolean("wantnc"), JIntW("negcnt"), JFloatW("ztz"), JFloatW("mingma"), JIntW("r"), JIntArray("isuppz"), JFloatW("nrminv"), JFloatW("resid"), JFloatW("rqcorr"), JFloatArray("work")),
    Routine   (             "slar2v",   JInt("n"), JFloatArray("x"), JFloatArray("y"), JFloatArray("z"), JInt("incx"), JFloatArray("c"), JFloatArray("s"), JInt("incc")),
    Routine   (             "slarf",    JString("side"), JInt("m"), JInt("n"), JFloatArray("v"), JInt("incv"), JFloat("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work")),
    Routine   (             "slarfb",   JString("side"), JString("trans"), JString("direct"), JString("storev"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("v"), JInt("ldv"), JFloatArray("t"), JInt("ldt"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JInt("ldwork")),
    Routine   (             "slarfg",   JInt("n"), JFloatW("alpha"), JFloatArray("x"), JInt("incx"), JFloatW("tau")),
    Routine   (             "slarft",   JString("direct"), JString("storev"), JInt("n"), JInt("k"), JFloatArray("v"), JInt("ldv"), JFloatArray("tau"), JFloatArray("t"), JInt("ldt")),
    Routine   (             "slarfx",   JString("side"), JInt("m"), JInt("n"), JFloatArray("v"), JFloat("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work")),
    Routine   (             "slargv",   JInt("n"), JFloatArray("x"), JInt("incx"), JFloatArray("y"), JInt("incy"), JFloatArray("c"), JInt("incc")),
    Routine   (             "slarnv",   JInt("idist"), JIntArray("iseed"), JInt("n"), JFloatArray("x")),
    Routine   (             "slarra",   JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloatArray("e2"), JFloat("spltol"), JFloat("tnrm"), JIntW("nsplit"), JIntArray("isplit"), JIntW("info")),
    Routine   (             "slarrb",   JInt("n"), JFloatArray("d"), JFloatArray("lld"), JInt("ifirst"), JInt("ilast"), JFloat("rtol1"), JFloat("rtol2"), JInt("offset"), JFloatArray("w"), JFloatArray("wgap"), JFloatArray("werr"), JFloatArray("work"), JIntArray("iwork"), JFloat("pivmin"), JFloat("spdiam"), JInt("twist"), JIntW("info")),
    Routine   (             "slarrc",   JString("jobt"), JInt("n"), JFloat("vl"), JFloat("vu"), JFloatArray("d"), JFloatArray("e"), JFloat("pivmin"), JIntW("eigcnt"), JIntW("lcnt"), JIntW("rcnt"), JIntW("info")),
    Routine   (             "slarrd",   JString("range"), JString("order"), JInt("n"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloatArray("gers"), JFloat("reltol"), JFloatArray("d"), JFloatArray("e"), JFloatArray("e2"), JFloat("pivmin"), JInt("nsplit"), JIntArray("isplit"), JIntW("m"), JFloatArray("w"), JFloatArray("werr"), JFloatW("wl"), JFloatW("wu"), JIntArray("iblock"), JIntArray("indexw"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slarre",   JString("range"), JInt("n"), JFloatW("vl"), JFloatW("vu"), JInt("il"), JInt("iu"), JFloatArray("d"), JFloatArray("e"), JFloatArray("e2"), JFloat("rtol1"), JFloat("rtol2"), JFloat("spltol"), JIntW("nsplit"), JIntArray("isplit"), JIntW("m"), JFloatArray("w"), JFloatArray("werr"), JFloatArray("wgap"), JIntArray("iblock"), JIntArray("indexw"), JFloatArray("gers"), JFloatW("pivmin"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slarrf",   JInt("n"), JFloatArray("d"), JFloatArray("l"), JFloatArray("ld"), JInt("clstrt"), JInt("clend"), JFloatArray("w"), JFloatArray("wgap"), JFloatArray("werr"), JFloat("spdiam"), JFloat("clgapl"), JFloat("clgapr"), JFloat("pivmin"), JFloatW("sigma"), JFloatArray("dplus"), JFloatArray("lplus"), JFloatArray("work"), JIntW("info")),
    Routine   (             "slarrj",   JInt("n"), JFloatArray("d"), JFloatArray("e2"), JInt("ifirst"), JInt("ilast"), JFloat("rtol"), JInt("offset"), JFloatArray("w"), JFloatArray("werr"), JFloatArray("work"), JIntArray("iwork"), JFloat("pivmin"), JFloat("spdiam"), JIntW("info")),
    Routine   (             "slarrk",   JInt("n"), JInt("iw"), JFloat("gl"), JFloat("gu"), JFloatArray("d"), JFloatArray("e2"), JFloat("pivmin"), JFloat("reltol"), JFloatW("w"), JFloatW("werr"), JIntW("info")),
    Routine   (             "slarrr",   JInt("n"), JFloatArray("d"), JFloatArray("e"), JIntW("info")),
    Routine   (             "slarrv",   JInt("n"), JFloat("vl"), JFloat("vu"), JFloatArray("d"), JFloatArray("l"), JFloat("pivmin"), JIntArray("isplit"), JInt("m"), JInt("dol"), JInt("dou"), JFloat("minrgp"), JFloatW("rtol1"), JFloatW("rtol2"), JFloatArray("w"), JFloatArray("werr"), JFloatArray("wgap"), JIntArray("iblock"), JIntArray("indexw"), JFloatArray("gers"), JFloatArray("z"), JInt("ldz"), JIntArray("isuppz"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slartg",   JFloat("f"), JFloat("g"), JFloatW("cs"), JFloatW("sn"), JFloatW("r")),
    Routine   (             "slartv",   JInt("n"), JFloatArray("x"), JInt("incx"), JFloatArray("y"), JInt("incy"), JFloatArray("c"), JFloatArray("s"), JInt("incc")),
    Routine   (             "slaruv",   JIntArray("iseed"), JInt("n"), JFloatArray("x")),
    Routine   (             "slarz",    JString("side"), JInt("m"), JInt("n"), JInt("l"), JFloatArray("v"), JInt("incv"), JFloat("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work")),
    Routine   (             "slarzb",   JString("side"), JString("trans"), JString("direct"), JString("storev"), JInt("m"), JInt("n"), JInt("k"), JInt("l"), JFloatArray("v"), JInt("ldv"), JFloatArray("t"), JInt("ldt"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JInt("ldwork")),
    Routine   (             "slarzt",   JString("direct"), JString("storev"), JInt("n"), JInt("k"), JFloatArray("v"), JInt("ldv"), JFloatArray("tau"), JFloatArray("t"), JInt("ldt")),
    Routine   (             "slas2",    JFloat("f"), JFloat("g"), JFloat("h"), JFloatW("ssmin"), JFloatW("ssmax")),
    Routine   (             "slascl",   JString("type"), JInt("kl"), JInt("ku"), JFloat("cfrom"), JFloat("cto"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "slasd0",   JInt("n"), JInt("sqre"), JFloatArray("d"), JFloatArray("e"), JFloatArray("u"), JInt("ldu"), JFloatArray("vt"), JInt("ldvt"), JInt("smlsiz"), JIntArray("iwork"), JFloatArray("work"), JIntW("info")),
    Routine   (             "slasd1",   JInt("nl"), JInt("nr"), JInt("sqre"), JFloatArray("d"), JFloatW("alpha"), JFloatW("beta"), JFloatArray("u"), JInt("ldu"), JFloatArray("vt"), JInt("ldvt"), JIntArray("idxq"), JIntArray("iwork"), JFloatArray("work"), JIntW("info")),
    Routine   (             "slasd2",   JInt("nl"), JInt("nr"), JInt("sqre"), JIntW("k"), JFloatArray("d"), JFloatArray("z"), JFloat("alpha"), JFloat("beta"), JFloatArray("u"), JInt("ldu"), JFloatArray("vt"), JInt("ldvt"), JFloatArray("dsigma"), JFloatArray("u2"), JInt("ldu2"), JFloatArray("vt2"), JInt("ldvt2"), JIntArray("idxp"), JIntArray("idx"), JIntArray("idxc"), JIntArray("idxq"), JIntArray("coltyp"), JIntW("info")),
    Routine   (             "slasd3",   JInt("nl"), JInt("nr"), JInt("sqre"), JInt("k"), JFloatArray("d"), JFloatArray("q"), JInt("ldq"), JFloatArray("dsigma"), JFloatArray("u"), JInt("ldu"), JFloatArray("u2"), JInt("ldu2"), JFloatArray("vt"), JInt("ldvt"), JFloatArray("vt2"), JInt("ldvt2"), JIntArray("idxc"), JIntArray("ctot"), JFloatArray("z"), JIntW("info")),
    Routine   (             "slasd4",   JInt("n"), JInt("i"), JFloatArray("d"), JFloatArray("z"), JFloatArray("delta"), JFloat("rho"), JFloatW("sigma"), JFloatArray("work"), JIntW("info")),
    Routine   (             "slasd5",   JInt("i"), JFloatArray("d"), JFloatArray("z"), JFloatArray("delta"), JFloat("rho"), JFloatW("dsigma"), JFloatArray("work")),
    Routine   (             "slasd6",   JInt("icompq"), JInt("nl"), JInt("nr"), JInt("sqre"), JFloatArray("d"), JFloatArray("vf"), JFloatArray("vl"), JFloatW("alpha"), JFloatW("beta"), JIntArray("idxq"), JIntArray("perm"), JIntW("givptr"), JIntArray("givcol"), JInt("ldgcol"), JFloatArray("givnum"), JInt("ldgnum"), JFloatArray("poles"), JFloatArray("difl"), JFloatArray("difr"), JFloatArray("z"), JIntW("k"), JFloatW("c"), JFloatW("s"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slasd7",   JInt("icompq"), JInt("nl"), JInt("nr"), JInt("sqre"), JIntW("k"), JFloatArray("d"), JFloatArray("z"), JFloatArray("zw"), JFloatArray("vf"), JFloatArray("vfw"), JFloatArray("vl"), JFloatArray("vlw"), JFloat("alpha"), JFloat("beta"), JFloatArray("dsigma"), JIntArray("idx"), JIntArray("idxp"), JIntArray("idxq"), JIntArray("perm"), JIntW("givptr"), JIntArray("givcol"), JInt("ldgcol"), JFloatArray("givnum"), JInt("ldgnum"), JFloatW("c"), JFloatW("s"), JIntW("info")),
    Routine   (             "slasd8",   JInt("icompq"), JInt("k"), JFloatArray("d"), JFloatArray("z"), JFloatArray("vf"), JFloatArray("vl"), JFloatArray("difl"), JFloatArray("difr"), JInt("lddifr"), JFloatArray("dsigma"), JFloatArray("work"), JIntW("info")),
    Routine   (             "slasda",   JInt("icompq"), JInt("smlsiz"), JInt("n"), JInt("sqre"), JFloatArray("d"), JFloatArray("e"), JFloatArray("u"), JInt("ldu"), JFloatArray("vt"), JIntArray("k"), JFloatArray("difl"), JFloatArray("difr"), JFloatArray("z"), JFloatArray("poles"), JIntArray("givptr"), JIntArray("givcol"), JInt("ldgcol"), JIntArray("perm"), JFloatArray("givnum"), JFloatArray("c"), JFloatArray("s"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "slasdq",   JString("uplo"), JInt("sqre"), JInt("n"), JInt("ncvt"), JInt("nru"), JInt("ncc"), JFloatArray("d"), JFloatArray("e"), JFloatArray("vt"), JInt("ldvt"), JFloatArray("u"), JInt("ldu"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JIntW("info")),
    Routine   (             "slasdt",   JInt("n"), JIntW("lvl"), JIntW("nd"), JIntArray("inode"), JIntArray("ndiml"), JIntArray("ndimr"), JInt("msub")),
    Routine   (             "slaset",   JString("uplo"), JInt("m"), JInt("n"), JFloat("alpha"), JFloat("beta"), JFloatArray("a"), JInt("lda")),
    Routine   (             "slasq1",   JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloatArray("work"), JIntW("info")),
    Routine   (             "slasq2",   JInt("n"), JFloatArray("z"), JIntW("info")),
    Routine   (             "slasq3",   JInt("i0"), JIntW("n0"), JFloatArray("z"), JInt("pp"), JFloatW("dmin"), JFloatW("sigma"), JFloatW("desig"), JFloatW("qmax"), JIntW("nfail"), JIntW("iter"), JIntW("ndiv"), JBoolean("ieee")),
    Routine   (             "slasq4",   JInt("i0"), JInt("n0"), JFloatArray("z"), JInt("pp"), JInt("n0in"), JFloat("dmin"), JFloat("dmin1"), JFloat("dmin2"), JFloat("dn"), JFloat("dn1"), JFloat("dn2"), JFloatW("tau"), JIntW("ttype")),
    Routine   (             "slasq5",   JInt("i0"), JInt("n0"), JFloatArray("z"), JInt("pp"), JFloat("tau"), JFloatW("dmin"), JFloatW("dmin1"), JFloatW("dmin2"), JFloatW("dn"), JFloatW("dnm1"), JFloatW("dnm2"), JBoolean("ieee")),
    Routine   (             "slasq6",   JInt("i0"), JInt("n0"), JFloatArray("z"), JInt("pp"), JFloatW("dmin"), JFloatW("dmin1"), JFloatW("dmin2"), JFloatW("dn"), JFloatW("dnm1"), JFloatW("dnm2")),
    Routine   (             "slasr",    JString("side"), JString("pivot"), JString("direct"), JInt("m"), JInt("n"), JFloatArray("c"), JFloatArray("s"), JFloatArray("a"), JInt("lda")),
    Routine   (             "slasrt",   JString("id"), JInt("n"), JFloatArray("d"), JIntW("info")),
    Routine   (             "slassq",   JInt("n"), JFloatArray("x"), JInt("incx"), JFloatW("scale"), JFloatW("sumsq")),
    Routine   (             "slasv2",   JFloat("f"), JFloat("g"), JFloat("h"), JFloatW("ssmin"), JFloatW("ssmax"), JFloatW("snr"), JFloatW("csr"), JFloatW("snl"), JFloatW("csl")),
    Routine   (             "slaswp",   JInt("n"), JFloatArray("a"), JInt("lda"), JInt("k1"), JInt("k2"), JIntArray("ipiv"), JInt("incx")),
    Routine   (             "slasy2",   JBoolean("ltranl"), JBoolean("ltranr"), JInt("isgn"), JInt("n1"), JInt("n2"), JFloatArray("tl"), JInt("ldtl"), JFloatArray("tr"), JInt("ldtr"), JFloatArray("b"), JInt("ldb"), JFloatW("scale"), JFloatArray("x"), JInt("ldx"), JFloatW("xnorm"), JIntW("info")),
    Routine   (             "slasyf",   JString("uplo"), JInt("n"), JInt("nb"), JIntW("kb"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JFloatArray("w"), JInt("ldw"), JIntW("info")),
    Routine   (             "slatbs",   JString("uplo"), JString("trans"), JString("diag"), JString("normin"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JFloatArray("x"), JFloatW("scale"), JFloatArray("cnorm"), JIntW("info")),
    Routine   (             "slatdf",   JInt("ijob"), JInt("n"), JFloatArray("z"), JInt("ldz"), JFloatArray("rhs"), JFloatW("rdsum"), JFloatW("rdscal"), JIntArray("ipiv"), JIntArray("jpiv")),
    Routine   (             "slatps",   JString("uplo"), JString("trans"), JString("diag"), JString("normin"), JInt("n"), JFloatArray("ap"), JFloatArray("x"), JFloatW("scale"), JFloatArray("cnorm"), JIntW("info")),
    Routine   (             "slatrd",   JString("uplo"), JInt("n"), JInt("nb"), JFloatArray("a"), JInt("lda"), JFloatArray("e"), JFloatArray("tau"), JFloatArray("w"), JInt("ldw")),
    Routine   (             "slatrs",   JString("uplo"), JString("trans"), JString("diag"), JString("normin"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("x"), JFloatW("scale"), JFloatArray("cnorm"), JIntW("info")),
    Routine   (             "slatrz",   JInt("m"), JInt("n"), JInt("l"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work")),
    Routine   (             "slatzm",   JString("side"), JInt("m"), JInt("n"), JFloatArray("v"), JInt("incv"), JFloat("tau"), JFloatArray("c1"), JFloatArray("c2"), JInt("Ldc"), JFloatArray("work")),
    Routine   (             "slauu2",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "slauum",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("info")),
    Routine_NI(             "slazq3",   JInt("i0"), JIntW("n0"), JFloatArray("z"), JInt("pp"), JFloatW("dmin"), JFloatW("sigma"), JFloatW("desig"), JFloatW("qmax"), JIntW("nfail"), JIntW("iter"), JIntW("ndiv"), JBoolean("ieee"), JIntW("ttype"), JFloatW("dmin1"), JFloatW("dmin2"), JFloatW("dn"), JFloatW("dn1"), JFloatW("dn2"), JFloatW("tau")),
    Routine_NI(             "slazq4",   JInt("i0"), JInt("n0"), JFloatArray("z"), JInt("pp"), JInt("n0in"), JFloat("dmin"), JFloat("dmin1"), JFloat("dmin2"), JFloat("dn"), JFloat("dn1"), JFloat("dn2"), JFloatW("tau"), JIntW("ttype"), JFloatW("g")),
    Routine   (             "sopgtr",   JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("tau"), JFloatArray("q"), JInt("ldq"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sopmtr",   JString("side"), JString("uplo"), JString("trans"), JInt("m"), JInt("n"), JFloatArray("ap"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sorg2l",   JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sorg2r",   JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sorgbr",   JString("vect"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sorghr",   JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sorgl2",   JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sorglq",   JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sorgql",   JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sorgqr",   JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sorgr2",   JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sorgrq",   JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sorgtr",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sorm2l",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sorm2r",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sormbr",   JString("vect"), JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sormhr",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sorml2",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sormlq",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sormql",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sormqr",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sormr2",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sormr3",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JInt("l"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sormrq",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sormrz",   JString("side"), JString("trans"), JInt("m"), JInt("n"), JInt("k"), JInt("l"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "sormtr",   JString("side"), JString("uplo"), JString("trans"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("c"), JInt("Ldc"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "spbcon",   JString("uplo"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JFloat("anorm"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "spbequ",   JString("uplo"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JFloatArray("s"), JFloatW("scond"), JFloatW("amax"), JIntW("info")),
    Routine   (             "spbrfs",   JString("uplo"), JInt("n"), JInt("kd"), JInt("nrhs"), JFloatArray("ab"), JInt("ldab"), JFloatArray("afb"), JInt("ldafb"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "spbstf",   JString("uplo"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JIntW("info")),
    Routine   (             "spbsv",    JString("uplo"), JInt("n"), JInt("kd"), JInt("nrhs"), JFloatArray("ab"), JInt("ldab"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "spbsvx",   JString("fact"), JString("uplo"), JInt("n"), JInt("kd"), JInt("nrhs"), JFloatArray("ab"), JInt("ldab"), JFloatArray("afb"), JInt("ldafb"), JStringW("equed"), JFloatArray("s"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatW("rcond"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "spbtf2",   JString("uplo"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JIntW("info")),
    Routine   (             "spbtrf",   JString("uplo"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JIntW("info")),
    Routine   (             "spbtrs",   JString("uplo"), JInt("n"), JInt("kd"), JInt("nrhs"), JFloatArray("ab"), JInt("ldab"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "spocon",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloat("anorm"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "spoequ",   JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("s"), JFloatW("scond"), JFloatW("amax"), JIntW("info")),
    Routine   (             "sporfs",   JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("af"), JInt("ldaf"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sposv",    JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sposvx",   JString("fact"), JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("af"), JInt("ldaf"), JStringW("equed"), JFloatArray("s"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatW("rcond"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "spotf2",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "spotrf",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "spotri",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "spotrs",   JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sppcon",   JString("uplo"), JInt("n"), JFloatArray("ap"), JFloat("anorm"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sppequ",   JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("s"), JFloatW("scond"), JFloatW("amax"), JIntW("info")),
    Routine   (             "spprfs",   JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("ap"), JFloatArray("afp"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sppsv",    JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("ap"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sppsvx",   JString("fact"), JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("ap"), JFloatArray("afp"), JStringW("equed"), JFloatArray("s"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatW("rcond"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "spptrf",   JString("uplo"), JInt("n"), JFloatArray("ap"), JIntW("info")),
    Routine   (             "spptri",   JString("uplo"), JInt("n"), JFloatArray("ap"), JIntW("info")),
    Routine   (             "spptrs",   JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("ap"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sptcon",   JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloat("anorm"), JFloatW("rcond"), JFloatArray("work"), JIntW("info")),
    Routine   (             "spteqr",   JString("compz"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sptrfs",   JInt("n"), JInt("nrhs"), JFloatArray("d"), JFloatArray("e"), JFloatArray("df"), JFloatArray("ef"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sptsv",    JInt("n"), JInt("nrhs"), JFloatArray("d"), JFloatArray("e"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sptsvx",   JString("fact"), JInt("n"), JInt("nrhs"), JFloatArray("d"), JFloatArray("e"), JFloatArray("df"), JFloatArray("ef"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatW("rcond"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntW("info")),
    Routine   (             "spttrf",   JInt("n"), JFloatArray("d"), JFloatArray("e"), JIntW("info")),
    Routine   (             "spttrs",   JInt("n"), JInt("nrhs"), JFloatArray("d"), JFloatArray("e"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sptts2",   JInt("n"), JInt("nrhs"), JFloatArray("d"), JFloatArray("e"), JFloatArray("b"), JInt("ldb")),
    Routine   (             "srscl",    JInt("n"), JFloat("sa"), JFloatArray("sx"), JInt("incx")),
    Routine   (             "ssbev",    JString("jobz"), JString("uplo"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntW("info")),
    Routine   (             "ssbevd",   JString("jobz"), JString("uplo"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "ssbevx",   JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JFloatArray("q"), JInt("ldq"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "ssbgst",   JString("vect"), JString("uplo"), JInt("n"), JInt("ka"), JInt("kb"), JFloatArray("ab"), JInt("ldab"), JFloatArray("bb"), JInt("ldbb"), JFloatArray("x"), JInt("ldx"), JFloatArray("work"), JIntW("info")),
    Routine   (             "ssbgv",    JString("jobz"), JString("uplo"), JInt("n"), JInt("ka"), JInt("kb"), JFloatArray("ab"), JInt("ldab"), JFloatArray("bb"), JInt("ldbb"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntW("info")),
    Routine   (             "ssbgvd",   JString("jobz"), JString("uplo"), JInt("n"), JInt("ka"), JInt("kb"), JFloatArray("ab"), JInt("ldab"), JFloatArray("bb"), JInt("ldbb"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "ssbgvx",   JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JInt("ka"), JInt("kb"), JFloatArray("ab"), JInt("ldab"), JFloatArray("bb"), JInt("ldbb"), JFloatArray("q"), JInt("ldq"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "ssbtrd",   JString("vect"), JString("uplo"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JFloatArray("d"), JFloatArray("e"), JFloatArray("q"), JInt("ldq"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sspcon",   JString("uplo"), JInt("n"), JFloatArray("ap"), JIntArray("ipiv"), JFloat("anorm"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sspev",    JString("jobz"), JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sspevd",   JString("jobz"), JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "sspevx",   JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JFloatArray("ap"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "sspgst",   JInt("itype"), JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("bp"), JIntW("info")),
    Routine   (             "sspgv",    JInt("itype"), JString("jobz"), JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("bp"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sspgvd",   JInt("itype"), JString("jobz"), JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("bp"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "sspgvx",   JInt("itype"), JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("bp"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "ssprfs",   JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("ap"), JFloatArray("afp"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sspsv",    JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("ap"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sspsvx",   JString("fact"), JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("ap"), JFloatArray("afp"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatW("rcond"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "ssptrd",   JString("uplo"), JInt("n"), JFloatArray("ap"), JFloatArray("d"), JFloatArray("e"), JFloatArray("tau"), JIntW("info")),
    Routine   (             "ssptrf",   JString("uplo"), JInt("n"), JFloatArray("ap"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "ssptri",   JString("uplo"), JInt("n"), JFloatArray("ap"), JIntArray("ipiv"), JFloatArray("work"), JIntW("info")),
    Routine   (             "ssptrs",   JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("ap"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "sstebz",   JString("range"), JString("order"), JInt("n"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JFloatArray("d"), JFloatArray("e"), JIntW("m"), JIntW("nsplit"), JFloatArray("w"), JIntArray("iblock"), JIntArray("isplit"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "sstedc",   JString("compz"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "sstegr",   JString("jobz"), JString("range"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JIntArray("isuppz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "sstein",   JInt("n"), JFloatArray("d"), JFloatArray("e"), JInt("m"), JFloatArray("w"), JIntArray("iblock"), JIntArray("isplit"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "sstemr",   JString("jobz"), JString("range"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JInt("nzc"), JIntArray("isuppz"), JBooleanW("tryrac"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "ssteqr",   JString("compz"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntW("info")),
    Routine   (             "ssterf",   JInt("n"), JFloatArray("d"), JFloatArray("e"), JIntW("info")),
    Routine   (             "sstev",    JString("jobz"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntW("info")),
    Routine   (             "sstevd",   JString("jobz"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "sstevr",   JString("jobz"), JString("range"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JIntArray("isuppz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "sstevx",   JString("jobz"), JString("range"), JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "ssycon",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JFloat("anorm"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "ssyev",    JString("jobz"), JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("w"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "ssyevd",   JString("jobz"), JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("w"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "ssyevr",   JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JIntArray("isuppz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "ssyevx",   JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "ssygs2",   JInt("itype"), JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "ssygst",   JInt("itype"), JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "ssygv",    JInt("itype"), JString("jobz"), JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("w"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "ssygvd",   JInt("itype"), JString("jobz"), JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("w"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "ssygvx",   JInt("itype"), JString("jobz"), JString("range"), JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloat("vl"), JFloat("vu"), JInt("il"), JInt("iu"), JFloat("abstol"), JIntW("m"), JFloatArray("w"), JFloatArray("z"), JInt("ldz"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JIntArray("ifail"), JIntW("info")),
    Routine   (             "ssyrfs",   JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("af"), JInt("ldaf"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "ssysv",    JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "ssysvx",   JString("fact"), JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("af"), JInt("ldaf"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatW("rcond"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "ssytd2",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("d"), JFloatArray("e"), JFloatArray("tau"), JIntW("info")),
    Routine   (             "ssytf2",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JIntW("info")),
    Routine   (             "ssytrd",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("d"), JFloatArray("e"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "ssytrf",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "ssytri",   JString("uplo"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JFloatArray("work"), JIntW("info")),
    Routine   (             "ssytrs",   JString("uplo"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JIntArray("ipiv"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "stbcon",   JString("norm"), JString("uplo"), JString("diag"), JInt("n"), JInt("kd"), JFloatArray("ab"), JInt("ldab"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "stbrfs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("kd"), JInt("nrhs"), JFloatArray("ab"), JInt("ldab"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "stbtrs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("kd"), JInt("nrhs"), JFloatArray("ab"), JInt("ldab"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "stgevc",   JString("side"), JString("howmny"), JBooleanArray("select"), JInt("n"), JFloatArray("s"), JInt("lds"), JFloatArray("p"), JInt("ldp"), JFloatArray("vl"), JInt("ldvl"), JFloatArray("vr"), JInt("ldvr"), JInt("mm"), JIntW("m"), JFloatArray("work"), JIntW("info")),
    Routine   (             "stgex2",   JBoolean("wantq"), JBoolean("wantz"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("q"), JInt("ldq"), JFloatArray("z"), JInt("ldz"), JInt("j1"), JInt("n1"), JInt("n2"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "stgexc",   JBoolean("wantq"), JBoolean("wantz"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("q"), JInt("ldq"), JFloatArray("z"), JInt("ldz"), JIntW("ifst"), JIntW("ilst"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    Routine   (             "stgsen",   JInt("ijob"), JBoolean("wantq"), JBoolean("wantz"), JBooleanArray("select"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("alphar"), JFloatArray("alphai"), JFloatArray("beta"), JFloatArray("q"), JInt("ldq"), JFloatArray("z"), JInt("ldz"), JIntW("m"), JFloatW("pl"), JFloatW("pr"), JFloatArray("dif"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "stgsja",   JString("jobu"), JString("jobv"), JString("jobq"), JInt("m"), JInt("p"), JInt("n"), JInt("k"), JInt("l"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloat("tola"), JFloat("tolb"), JFloatArray("alpha"), JFloatArray("beta"), JFloatArray("u"), JInt("ldu"), JFloatArray("v"), JInt("ldv"), JFloatArray("q"), JInt("ldq"), JFloatArray("work"), JIntW("ncycle"), JIntW("info")),
    Routine   (             "stgsna",   JString("job"), JString("howmny"), JBooleanArray("select"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("vl"), JInt("ldvl"), JFloatArray("vr"), JInt("ldvr"), JFloatArray("s"), JFloatArray("dif"), JInt("mm"), JIntW("m"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "stgsy2",   JString("trans"), JInt("ijob"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("c"), JInt("Ldc"), JFloatArray("d"), JInt("ldd"), JFloatArray("e"), JInt("lde"), JFloatArray("f"), JInt("ldf"), JFloatW("scale"), JFloatW("rdsum"), JFloatW("rdscal"), JIntArray("iwork"), JIntW("pq"), JIntW("info")),
    Routine   (             "stgsyl",   JString("trans"), JInt("ijob"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("c"), JInt("Ldc"), JFloatArray("d"), JInt("ldd"), JFloatArray("e"), JInt("lde"), JFloatArray("f"), JInt("ldf"), JFloatW("scale"), JFloatW("dif"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "stpcon",   JString("norm"), JString("uplo"), JString("diag"), JInt("n"), JFloatArray("ap"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "stprfs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("nrhs"), JFloatArray("ap"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "stptri",   JString("uplo"), JString("diag"), JInt("n"), JFloatArray("ap"), JIntW("info")),
    Routine   (             "stptrs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("nrhs"), JFloatArray("ap"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "strcon",   JString("norm"), JString("uplo"), JString("diag"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatW("rcond"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "strevc",   JString("side"), JString("howmny"), JBooleanArray("select"), JInt("n"), JFloatArray("t"), JInt("ldt"), JFloatArray("vl"), JInt("ldvl"), JFloatArray("vr"), JInt("ldvr"), JInt("mm"), JIntW("m"), JFloatArray("work"), JIntW("info")),
    Routine   (             "strexc",   JString("compq"), JInt("n"), JFloatArray("t"), JInt("ldt"), JFloatArray("q"), JInt("ldq"), JIntW("ifst"), JIntW("ilst"), JFloatArray("work"), JIntW("info")),
    Routine   (             "strrfs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("x"), JInt("ldx"), JFloatArray("ferr"), JFloatArray("berr"), JFloatArray("work"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "strsen",   JString("job"), JString("compq"), JBooleanArray("select"), JInt("n"), JFloatArray("t"), JInt("ldt"), JFloatArray("q"), JInt("ldq"), JFloatArray("wr"), JFloatArray("wi"), JIntW("m"), JFloatW("s"), JFloatW("sep"), JFloatArray("work"), JInt("lwork"), JIntArray("iwork"), JInt("liwork"), JIntW("info")),
    Routine   (             "strsna",   JString("job"), JString("howmny"), JBooleanArray("select"), JInt("n"), JFloatArray("t"), JInt("ldt"), JFloatArray("vl"), JInt("ldvl"), JFloatArray("vr"), JInt("ldvr"), JFloatArray("s"), JFloatArray("sep"), JInt("mm"), JIntW("m"), JFloatArray("work"), JInt("ldwork"), JIntArray("iwork"), JIntW("info")),
    Routine   (             "strsyl",   JString("trana"), JString("tranb"), JInt("isgn"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JFloatArray("c"), JInt("Ldc"), JFloatW("scale"), JIntW("info")),
    Routine   (             "strti2",   JString("uplo"), JString("diag"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "strtri",   JString("uplo"), JString("diag"), JInt("n"), JFloatArray("a"), JInt("lda"), JIntW("info")),
    Routine   (             "strtrs",   JString("uplo"), JString("trans"), JString("diag"), JInt("n"), JInt("nrhs"), JFloatArray("a"), JInt("lda"), JFloatArray("b"), JInt("ldb"), JIntW("info")),
    Routine   (             "stzrqf",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JIntW("info")),
    Routine   (             "stzrzf",   JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JFloatArray("tau"), JFloatArray("work"), JInt("lwork"), JIntW("info")),
    RoutineR  (JDoubleR(),  "dlamch",   JString("cmach")),
    Routine_NI(             "dlamc1",   JIntW("beta"), JIntW("t"), JBooleanW("rnd"), JBooleanW("ieee1")),
    Routine_NI(             "dlamc2",   JIntW("beta"), JIntW("t"), JBooleanW("rnd"), JDoubleW("eps"), JIntW("emin"), JDoubleW("rmin"), JIntW("emax"), JDoubleW("rmax")),
    RoutineR  (JDoubleR(),  "dlamc3",   JDouble("a"), JDouble("b")),
    Routine_NI(             "dlamc4",   JIntW("emin"), JDouble("start"), JInt("base")),
    Routine_NI(             "dlamc5",   JInt("beta"), JInt("p"), JInt("emin"), JBoolean("ieee"), JIntW("emax"), JDoubleW("rmax")),
    RoutineR  (JDoubleR(),  "dsecnd"),
    RoutineR  (JBooleanR(), "lsame",    JString("ca"), JString("cb")),
    RoutineR  (JFloatR(),   "second"),
    RoutineR  (JFloatR(),   "slamch",   JString("cmach")),
    Routine_NI(             "slamc1",   JIntW("beta"), JIntW("t"), JBooleanW("rnd"), JBooleanW("ieee1")),
    Routine_NI(             "slamc2",   JIntW("beta"), JIntW("t"), JBooleanW("rnd"), JFloatW("eps"), JIntW("emin"), JFloatW("rmin"), JIntW("emax"), JFloatW("rmax")),
    RoutineR  (JFloatR(),   "slamc3",   JFloat("a"), JFloat("b")),
    Routine_NI(             "slamc4",   JIntW("emin"), JFloat("start"), JInt("base")),
    Routine_NI(             "slamc5",   JInt("beta"), JInt("p"), JInt("emin"), JBoolean("ieee"), JIntW("emax"), JFloatW("rmax")),
  )

if sys.argv[1] == "arpack":
  Library("arpack", "libarpack.so.2",
    Routine   (         "dmout",  JInt("lout"), JInt("m"), JInt("n"), JDoubleArray("a"), JInt("lda"), JInt("idigit"), JString("ifmt")),
    Routine   (         "smout",  JInt("lout"), JInt("m"), JInt("n"), JFloatArray("a"), JInt("lda"), JInt("idigit"), JString("ifmt")),
    Routine   (         "dvout",  JInt("lout"), JInt("n"), JDoubleArray("sx"), JInt("idigit"), JString("ifmt")),
    Routine   (         "svout",  JInt("lout"), JInt("n"), JFloatArray("sx"), JInt("idigit"), JString("ifmt")),
    Routine   (         "ivout",  JInt("lout"), JInt("n"), JIntArray("ix"), JInt("idigit"), JString("ifmt")),
    Routine   (         "dgetv0", JIntW("ido"), JString("bmat"), JInt("itry"), JBoolean("initv"), JInt("n"), JInt("j"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("resid"), JDoubleW("rnorm"), JIntArray("ipntr"), JDoubleArray("workd"), JIntW("ierr")),
    Routine   (         "sgetv0", JIntW("ido"), JString("bmat"), JInt("itry"), JBoolean("initv"), JInt("n"), JInt("j"), JFloatArray("v"), JInt("ldv"), JFloatArray("resid"), JFloatW("rnorm"), JIntArray("ipntr"), JFloatArray("workd"), JIntW("ierr")),
    Routine_NI(         "dlaqrb", JBoolean("wantt"), JInt("n"), JInt("ilo"), JInt("ihi"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("wr"), JDoubleArray("wi"), JDoubleArray("z"), JIntW("info")),
    Routine_NI(         "slaqrb", JBoolean("wantt"), JInt("n"), JInt("ilo"), JInt("ihi"), JFloatArray("h"), JInt("ldh"), JFloatArray("wr"), JFloatArray("wi"), JFloatArray("z"), JIntW("info")),
    Routine   (         "dnaitr", JIntW("ido"), JString("bmat"), JInt("n"), JInt("k"), JInt("np"), JInt("nb"), JDoubleArray("resid"), JDoubleW("rnorm"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("h"), JInt("ldh"), JIntArray("ipntr"), JDoubleArray("workd"), JIntW("info")),
    Routine   (         "snaitr", JIntW("ido"), JString("bmat"), JInt("n"), JInt("k"), JInt("np"), JInt("nb"), JFloatArray("resid"), JFloatW("rnorm"), JFloatArray("v"), JInt("ldv"), JFloatArray("h"), JInt("ldh"), JIntArray("ipntr"), JFloatArray("workd"), JIntW("info")),
    Routine   (         "dnapps", JInt("n"), JIntW("kev"), JInt("np"), JDoubleArray("shiftr"), JDoubleArray("shifti"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("resid"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("workl"), JDoubleArray("workd")),
    Routine   (         "snapps", JInt("n"), JIntW("kev"), JInt("np"), JFloatArray("shiftr"), JFloatArray("shifti"), JFloatArray("v"), JInt("ldv"), JFloatArray("h"), JInt("ldh"), JFloatArray("resid"), JFloatArray("q"), JInt("ldq"), JFloatArray("workl"), JFloatArray("workd")),
    Routine   (         "dnaup2", JIntW("ido"), JString("bmat"), JInt("n"), JString("which"), JIntW("nev"), JIntW("np"), JDouble("tol"), JDoubleArray("resid"), JInt("mode"), JInt("iupd"), JInt("ishift"), JIntW("mxiter"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("ritzr"), JDoubleArray("ritzi"), JDoubleArray("bounds"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("workl"), JIntArray("ipntr"), JDoubleArray("workd"), JIntW("info")),
    Routine   (         "snaup2", JIntW("ido"), JString("bmat"), JInt("n"), JString("which"), JIntW("nev"), JIntW("np"), JFloat("tol"), JFloatArray("resid"), JInt("mode"), JInt("iupd"), JInt("ishift"), JIntW("mxiter"), JFloatArray("v"), JInt("ldv"), JFloatArray("h"), JInt("ldh"), JFloatArray("ritzr"), JFloatArray("ritzi"), JFloatArray("bounds"), JFloatArray("q"), JInt("ldq"), JFloatArray("workl"), JIntArray("ipntr"), JFloatArray("workd"), JIntW("info")),
    Routine   (         "dnaupd", JIntW("ido"), JString("bmat"), JInt("n"), JString("which"), JInt("nev"), JDoubleW("tol"), JDoubleArray("resid"), JInt("ncv"), JDoubleArray("v"), JInt("ldv"), JIntArray("iparam"), JIntArray("ipntr"), JDoubleArray("workd"), JDoubleArray("workl"), JInt("lworkl"), JIntW("info")),
    Routine   (         "snaupd", JIntW("ido"), JString("bmat"), JInt("n"), JString("which"), JInt("nev"), JFloatW("tol"), JFloatArray("resid"), JInt("ncv"), JFloatArray("v"), JInt("ldv"), JIntArray("iparam"), JIntArray("ipntr"), JFloatArray("workd"), JFloatArray("workl"), JInt("lworkl"), JIntW("info")),
    Routine   (         "dnconv", JInt("n"), JDoubleArray("ritzr"), JDoubleArray("ritzi"), JDoubleArray("bounds"), JDouble("tol"), JIntW("nconv")),
    Routine   (         "snconv", JInt("n"), JFloatArray("ritzr"), JFloatArray("ritzi"), JFloatArray("bounds"), JFloat("tol"), JIntW("nconv")),
    Routine   (         "dsconv", JInt("n"), JDoubleArray("ritz"), JDoubleArray("bounds"), JDouble("tol"), JIntW("nconv")),
    Routine   (         "ssconv", JInt("n"), JFloatArray("ritz"), JFloatArray("bounds"), JFloat("tol"), JIntW("nconv")),
    Routine   (         "dneigh", JDouble("rnorm"), JIntW("n"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("ritzr"), JDoubleArray("ritzi"), JDoubleArray("bounds"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("workl"), JIntW("ierr")),
    Routine   (         "sneigh", JFloat("rnorm"), JIntW("n"), JFloatArray("h"), JInt("ldh"), JFloatArray("ritzr"), JFloatArray("ritzi"), JFloatArray("bounds"), JFloatArray("q"), JInt("ldq"), JFloatArray("workl"), JIntW("ierr")),
    Routine   (         "dneupd", JBoolean("rvec"), JString("howmny"), JBooleanArray("select"), JDoubleArray("dr"), JDoubleArray("di"), JDoubleArray("z"), JInt("ldz"), JDouble("sigmar"), JDouble("sigmai"), JDoubleArray("workev"), JString("bmat"), JInt("n"), JString("which"), JIntW("nev"), JDouble("tol"), JDoubleArray("resid"), JInt("ncv"), JDoubleArray("v"), JInt("ldv"), JIntArray("iparam"), JIntArray("ipntr"), JDoubleArray("workd"), JDoubleArray("workl"), JInt("lworkl"), JIntW("info")),
    Routine   (         "sneupd", JBoolean("rvec"), JString("howmny"), JBooleanArray("select"), JFloatArray("dr"), JFloatArray("di"), JFloatArray("z"), JInt("ldz"), JFloat("sigmar"), JFloat("sigmai"), JFloatArray("workev"), JString("bmat"), JInt("n"), JString("which"), JIntW("nev"), JFloat("tol"), JFloatArray("resid"), JInt("ncv"), JFloatArray("v"), JInt("ldv"), JIntArray("iparam"), JIntArray("ipntr"), JFloatArray("workd"), JFloatArray("workl"), JInt("lworkl"), JIntW("info")),
    Routine   (         "dngets", JInt("ishift"), JString("which"), JIntW("kev"), JIntW("np"), JDoubleArray("ritzr"), JDoubleArray("ritzi"), JDoubleArray("bounds"), JDoubleArray("shiftr"), JDoubleArray("shifti")),
    Routine   (         "sngets", JInt("ishift"), JString("which"), JIntW("kev"), JIntW("np"), JFloatArray("ritzr"), JFloatArray("ritzi"), JFloatArray("bounds"), JFloatArray("shiftr"), JFloatArray("shifti")),
    Routine   (         "dsaitr", JIntW("ido"), JString("bmat"), JInt("n"), JInt("k"), JInt("np"), JInt("mode"), JDoubleArray("resid"), JDoubleW("rnorm"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("h"), JInt("ldh"), JIntArray("ipntr"), JDoubleArray("workd"), JIntW("info")),
    Routine   (         "ssaitr", JIntW("ido"), JString("bmat"), JInt("n"), JInt("k"), JInt("np"), JInt("mode"), JFloatArray("resid"), JFloatW("rnorm"), JFloatArray("v"), JInt("ldv"), JFloatArray("h"), JInt("ldh"), JIntArray("ipntr"), JFloatArray("workd"), JIntW("info")),
    Routine   (         "dsapps", JInt("n"), JInt("kev"), JInt("np"), JDoubleArray("shift"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("resid"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("workd")),
    Routine   (         "ssapps", JInt("n"), JInt("kev"), JInt("np"), JFloatArray("shift"), JFloatArray("v"), JInt("ldv"), JFloatArray("h"), JInt("ldh"), JFloatArray("resid"), JFloatArray("q"), JInt("ldq"), JFloatArray("workd")),
    Routine   (         "dsaup2", JIntW("ido"), JString("bmat"), JInt("n"), JString("which"), JIntW("nev"), JIntW("np"), JDouble("tol"), JDoubleArray("resid"), JInt("mode"), JInt("iupd"), JInt("ishift"), JIntW("mxiter"), JDoubleArray("v"), JInt("ldv"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("ritz"), JDoubleArray("bounds"), JDoubleArray("q"), JInt("ldq"), JDoubleArray("workl"), JIntArray("ipntr"), JDoubleArray("workd"), JIntW("info")),
    Routine   (         "ssaup2", JIntW("ido"), JString("bmat"), JInt("n"), JString("which"), JIntW("nev"), JIntW("np"), JFloat("tol"), JFloatArray("resid"), JInt("mode"), JInt("iupd"), JInt("ishift"), JIntW("mxiter"), JFloatArray("v"), JInt("ldv"), JFloatArray("h"), JInt("ldh"), JFloatArray("ritz"), JFloatArray("bounds"), JFloatArray("q"), JInt("ldq"), JFloatArray("workl"), JIntArray("ipntr"), JFloatArray("workd"), JIntW("info")),
    Routine   (         "dseigt", JDouble("rnorm"), JInt("n"), JDoubleArray("h"), JInt("ldh"), JDoubleArray("eig"), JDoubleArray("bounds"), JDoubleArray("workl"), JIntW("ierr")),
    Routine   (         "sseigt", JFloat("rnorm"), JInt("n"), JFloatArray("h"), JInt("ldh"), JFloatArray("eig"), JFloatArray("bounds"), JFloatArray("workl"), JIntW("ierr")),
    Routine   (         "dsesrt", JString("which"), JBoolean("apply"), JInt("n"), JDoubleArray("x"), JInt("na"), JDoubleArray("a"), JInt("lda")),
    Routine   (         "ssesrt", JString("which"), JBoolean("apply"), JInt("n"), JFloatArray("x"), JInt("na"), JFloatArray("a"), JInt("lda")),
    Routine   (         "dsaupd", JIntW("ido"), JString("bmat"), JInt("n"), JString("which"), JInt("nev"), JDoubleW("tol"), JDoubleArray("resid"), JInt("ncv"), JDoubleArray("v"), JInt("ldv"), JIntArray("iparam"), JIntArray("ipntr"), JDoubleArray("workd"), JDoubleArray("workl"), JInt("lworkl"), JIntW("info")),
    Routine   (         "ssaupd", JIntW("ido"), JString("bmat"), JInt("n"), JString("which"), JInt("nev"), JFloatW("tol"), JFloatArray("resid"), JInt("ncv"), JFloatArray("v"), JInt("ldv"), JIntArray("iparam"), JIntArray("ipntr"), JFloatArray("workd"), JFloatArray("workl"), JInt("lworkl"), JIntW("info")),
    Routine   (         "dseupd", JBoolean("rvec"), JString("howmny"), JBooleanArray("select"), JDoubleArray("d"), JDoubleArray("z"), JInt("ldz"), JDouble("sigma"), JString("bmat"), JInt("n"), JString("which"), JIntW("nev"), JDouble("tol"), JDoubleArray("resid"), JInt("ncv"), JDoubleArray("v"), JInt("ldv"), JIntArray("iparam"), JIntArray("ipntr"), JDoubleArray("workd"), JDoubleArray("workl"), JInt("lworkl"), JIntW("info")),
    Routine   (         "sseupd", JBoolean("rvec"), JString("howmny"), JBooleanArray("select"), JFloatArray("d"), JFloatArray("z"), JInt("ldz"), JFloat("sigma"), JString("bmat"), JInt("n"), JString("which"), JIntW("nev"), JFloat("tol"), JFloatArray("resid"), JInt("ncv"), JFloatArray("v"), JInt("ldv"), JIntArray("iparam"), JIntArray("ipntr"), JFloatArray("workd"), JFloatArray("workl"), JInt("lworkl"), JIntW("info")),
    Routine   (         "dsgets", JInt("ishift"), JString("which"), JIntW("kev"), JIntW("np"), JDoubleArray("ritz"), JDoubleArray("bounds"), JDoubleArray("shifts")),
    Routine   (         "ssgets", JInt("ishift"), JString("which"), JIntW("kev"), JIntW("np"), JFloatArray("ritz"), JFloatArray("bounds"), JFloatArray("shifts")),
    Routine   (         "dsortc", JString("which"), JBoolean("apply"), JInt("n"), JDoubleArray("xreal"), JDoubleArray("ximag"), JDoubleArray("y")),
    Routine   (         "ssortc", JString("which"), JBoolean("apply"), JInt("n"), JFloatArray("xreal"), JFloatArray("ximag"), JFloatArray("y")),
    Routine   (         "dsortr", JString("which"), JBoolean("apply"), JInt("n"), JDoubleArray("x1"), JDoubleArray("x2")),
    Routine   (         "ssortr", JString("which"), JBoolean("apply"), JInt("n"), JFloatArray("x1"), JFloatArray("x2")),
    Routine   (         "dstatn"),
    Routine   (         "sstatn"),
    Routine   (         "dstats"),
    Routine   (         "sstats"),
    Routine   (         "dstqrb", JInt("n"), JDoubleArray("d"), JDoubleArray("e"), JDoubleArray("z"), JDoubleArray("work"), JIntW("info")),
    Routine   (         "sstqrb", JInt("n"), JFloatArray("d"), JFloatArray("e"), JFloatArray("z"), JFloatArray("work"), JIntW("info")),
    RoutineR  (JIntR(), "icnteq", JInt("n"), JIntArray("array"), JInt("value")),
    Routine   (         "icopy",  JInt("n"), JIntArray("lx"), JInt("incx"), JIntArray("ly"), JInt("incy")),
    Routine   (         "iset",   JInt("n"), JInt("value"), JIntArray("array"), JInt("inc")),
    Routine   (         "iswap",  JInt("n"), JIntArray("sx"), JInt("incx"), JIntArray("sy"), JInt("incy")),
    Routine   (         "second", JFloatW("t")),
  )