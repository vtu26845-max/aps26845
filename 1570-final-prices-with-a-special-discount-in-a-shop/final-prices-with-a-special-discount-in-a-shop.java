class Solution {
    public int[] finalPrices(int[] prices) 
    {
        int Arr[] = new int[prices.length];
        for(int i = 0; i < prices.length; i++)
        {
            int iIndex = i+1;
            int iFlag = 0;
            while(iIndex != prices.length)
            {
                if(prices[iIndex] <= prices[i])
                {
                    Arr[i] = prices[i] - prices[iIndex];
                    iIndex++;
                    iFlag = 1;
                    break;
                }
                iIndex++;
            }
            if(iFlag == 0)
            {
                Arr[i] = prices[i];
            }
        }
        return Arr;
    }
}