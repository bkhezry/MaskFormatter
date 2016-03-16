# MaskFormatter
MaskFormatter adds mask functionality to your EditText. It will prevent user from inserting not allowed signs, and format input as well.

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

Please copy and paste three files from formatter folder - `MaskFormatter.java`, `CarTransforms.java` and `InvalidTextException.java`.

Then you can use it like this:

```
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
```