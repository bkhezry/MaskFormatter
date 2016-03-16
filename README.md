# MaskFormatter
MaskFormatter adds mask functionality to your EditText. It will prevent user from inserting not allowed signs, and format input as well.

![MaskFormatter](https://raw.githubusercontent.com/AzimoLabs/MaskFormatter/master/art/screenshot.png)

Mask should be built from characters listed below (java regex associated to character given after colon):

```
'9': '[0-9]',
'8': '[0-8]',
'7': '[0-7]',
'6': '[0-6]',
'5': '[0-5]',
'4': '[0-4]',
'3': '[0-3]',
'2': '[0-2]',
'1': '[0-1]',
'0': '[0]',

'*': '.',
'W': '\W',
'd': '\d',
'D': '\D',
's': '\s',
'S': '\S',

'A': '[A-Z]',
'a': '[a-z]',
'Z': '[A-ZÇÀÁÂÃÈÉÊẼÌÍÎĨÒÓÔÕÙÚÛŨ]',
'z': '[a-zçáàãâéèêẽíìĩîóòôõúùũüû]',
'@': '[a-zA-Z]',
'#': '[a-zA-ZçáàãâéèêẽíìĩîóòôõúùũüûÇÀÁÂÃÈÉÊẼÌÍÎĨÒÓÔÕÙÚÛŨ]',

'%': '[A-Z0-9]',
'w': '[a-zA-Z0-9]'
```

# Usage

Please copy and paste three files from [maskformatter/](https://github.com/AzimoLabs/MaskFormatter/tree/master/maskformatter/src/main/java/com/azimolabs/maskformatter) directory:

- `MaskFormatter.java`
- `CharTransforms.java` 
- `InvalidTextException.java`


Then you can use it like this:

```java
public MainActivity extends Activity {

     private static final IBAN_MASK = „AA 99 9999 AAAA wwww wwww wwww”;

     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.sample_layout);

          EditText ibanEditText = (EditText) findViewById(R.id.etIban);
          MaskFormatter ibanMaskFormatter = new MaskFormatter(IBAN_MASK, ibanEditText);
          ibanEditText.addTextChangedListener(ibanMaskFormatter);
     }

}
```java

# License

```
	Copyright (C) 2016 AzimoLabs

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
