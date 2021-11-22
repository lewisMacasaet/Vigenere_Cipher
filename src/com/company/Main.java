import java.util.Scanner;
import java.util.Arrays;
import java.lang.StringBuffer;

/**
 * Author: Lewis M
 * Date: 10/19/2021
 * Sources used in creation of this program:
 *  1. https://gist.github.com/dssstr/aedbb5e9f2185f366c6d6b50fad3e4a4
 *  2. https://pages.mtu.edu/~shene/NSF-4/Tutorial/VIG/Vig-Algebraic.html
 *  3. https://stackoverflow.com/questions/10006165/converting-string-to-character-array-in-java
 */

public class Main
{
    private static final String[] alphabetArray = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U", "V", "W", "X", "Y", "Z"};
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String userKeyInput(String introMsg1)
    {
        Scanner sc1 = new Scanner(System.in);
        System.out.println(introMsg1);
        String myKey = sc1.nextLine();
        return myKey;
    }

    public static String userMessageInput(String introMsg2)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(introMsg2);
        String myInput = sc.nextLine();
        return myInput;
    }

    public static String VigEncrypter(String userMessage, String userKey)
    {
        String messageCopy = userMessage;
        String[] arrayMessageCopy = messageCopy.split("");
        String keyboardCharacters = "!@#$%^&()_+-=,<.>?/:;\"}]1234567890 ";
        String[] keyboardCharactersCopy = keyboardCharacters.split("");
        // System.out.println(Arrays.toString(keyboardCharactersCopy));
        int counter3 = 0;
        //int co = 0;
        int[] keyboardCharactersIndex = new int[counter3];
        int[] locationOfCharactersOnMessage = new int[counter3];


        for(int i = 0; i < arrayMessageCopy.length; i++)
        {
            if(keyboardCharacters.indexOf(arrayMessageCopy[i]) != -1)
            {
                counter3 += 1;
                //System.out.println(keyboardCharacters.indexOf(arrayMessageCopy[i]));
                userMessage = userMessage.replace(keyboardCharactersCopy[keyboardCharacters.indexOf(arrayMessageCopy[i])], "");
                locationOfCharactersOnMessage = Arrays.copyOf(locationOfCharactersOnMessage, counter3);
                keyboardCharactersIndex = Arrays.copyOf(keyboardCharactersIndex, counter3);
                //locationOfCharactersOnMessage[counter3 - 1] = i;
                //System.out.println(i);
                locationOfCharactersOnMessage[counter3 - 1] = i;
                keyboardCharactersIndex[counter3 - 1] = keyboardCharacters.indexOf(arrayMessageCopy[i]);
            }
        }


        //System.out.println("KEYBOARD CHAR INDEXES IN ARRAY: " + Arrays.toString(keyboardCharactersIndex));
        //System.out.println("KEYBOARD CHAR IN MESSAGE: " + Arrays.toString(locationOfCharactersOnMessage));

        //System.out.println(arrayMessageCopy.length);
        //System.out.println(userMessage);
        userMessage = userMessage.toUpperCase();
        userKey = userKey.toUpperCase();

        String[] arrayMessage = userMessage.split("");
        String[] arrayUserKey = userKey.split("");

        int[] arrayMessageIndexes = new int[arrayMessage.length];
        int[] arrayUserKeyIndexes = new int[arrayUserKey.length];
        int counter = 0;

        for(int i = 0; i < arrayMessage.length; i++)
        {
            if(alphabet.indexOf(arrayMessage[i]) != -1)
            {
                arrayMessageIndexes[i] = alphabet.indexOf(arrayMessage[i]);
            }
        }

        for(int x = 0; x < arrayUserKey.length; x++)
        {
            if (alphabet.indexOf(arrayUserKey[x]) != -1)
            {
                arrayUserKeyIndexes[x] = alphabet.indexOf(arrayUserKey[x]);
            }
        }


        //System.out.println(Arrays.toString(arrayUserKeyIndexes));
        //System.out.println(Arrays.toString(arrayMessageIndexes));

        if(arrayMessageIndexes.length <= arrayUserKeyIndexes.length)
        {
            arrayUserKeyIndexes = Arrays.copyOfRange(arrayUserKeyIndexes, 0, arrayMessageIndexes.length);
        }
        else
        {
            int previousStringArrayLength = arrayUserKeyIndexes.length;
            int keyLength = arrayMessageIndexes.length - arrayUserKeyIndexes.length;
            arrayUserKeyIndexes = Arrays.copyOf(arrayUserKeyIndexes, arrayUserKeyIndexes.length + keyLength);
            //int counter = 0;

            for(int i = arrayUserKeyIndexes.length - keyLength; i < arrayUserKeyIndexes.length; i++)
            {
                arrayUserKeyIndexes[i] = arrayUserKeyIndexes[counter];
                counter += 1;
            }
        }

        String encryptedString = "";

        for(int y = 0; y < arrayUserKeyIndexes.length; y++)
        {

            int shiftedIndex = (arrayUserKeyIndexes[y] + arrayMessageIndexes[y]) % 26;
            encryptedString += alphabetArray[shiftedIndex];

        }


        //for(int x = 0; x < encryptedString.length; x++)
        //System.out.println(encryptedString);
        //System.out.println(encryptedString.length());
        StringBuffer encryptedString1 = new StringBuffer(encryptedString);

        for(int i = 0; i < keyboardCharactersIndex.length; i++)
        {
            encryptedString1.insert(locationOfCharactersOnMessage[i] , keyboardCharactersCopy[keyboardCharactersIndex[i]].charAt(0));
        }


        return encryptedString1.toString();
    }

    public static void main(String[] args)
    {
        String intro1 = "Enter a shift key: ";
        String intro2 = "Enter a message to be ciphered: ";
        String myKey = userKeyInput(intro1);
        String myMessage = userMessageInput(intro2);

        String test = VigEncrypter(myMessage, myKey);
        System.out.println("Encrypted string: " + test);
    }
}