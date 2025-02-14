package aps2.suffixarray;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;

public class PublicTests extends TestCase {
	SuffixArrayIndex sa;
	
	final String text = "mississippi";
	
	protected void setUp() throws Exception {
		sa = new SuffixArrayIndex(text);
	}
	
	public void testSuffixArray() {
		int[] resultingSA = new int[]{10,7,4,1,0,9,8,6,3,5,2};
		assertTrue(Arrays.equals(resultingSA, sa.getSuffixArray()));
	}
	
	public void testLocate() {
		String query = "s";
		Set<Integer> locations = new HashSet(Arrays.asList(2,3,5,6));
		
		assertEquals(locations, sa.locate(query));
	}

	public void testLocateEmpty() {
        String query = "";
        Set<Integer> locations = new HashSet(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10));

        assertEquals(locations, sa.locate(query));
    }

	public void testLongestRepeatedSubstring() {
		assertEquals("issi", sa.longestRepeatedSubstring());
	}
	
	public void testLongestCommonPrefixLen() {
		assertEquals(0, sa.longestCommonPrefixLen(0, 1)); // none
		assertEquals(1, sa.longestCommonPrefixLen(4, 7)); // i
		assertEquals(4, sa.longestCommonPrefixLen(1, 4)); // issi
	}

	public void testSuffixSuffixCompare(){
        boolean result = sa.suffixSuffixCompare(7,3);
        assertEquals(result, true);

        result = sa.suffixSuffixCompare(2,10);
        assertEquals(result, false);
    }

    public void testStringSuffixCompare(){
        boolean result = sa.stringSuffixCompare("ppi",5);
        assertEquals(result, true);

        result = sa.stringSuffixCompare("sippi", 6);
        assertEquals(result, true);
    }
}
