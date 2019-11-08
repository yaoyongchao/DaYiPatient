package com.fh.cplib.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MD5Util {

    fun verify(content: String, sign: String, publicKey: String): Boolean {
        return true
    }

    companion object {

        fun signBySec(content: String, mackey: String): String? {
            val orgcontent = content + mackey
            return md5(orgcontent.toByteArray())
        }


        fun verifysign(orgdata: String, sign: String, mackey: String): Boolean {
            try {
                val md5ret = md5((orgdata + mackey).toByteArray())
                return if (md5ret == sign)
                    true
                else
                    false
            } catch (e: Exception) {
                e.printStackTrace()
                return false
            }

        }

        fun ByteArrayToHexString(d: ByteArray?): String {
            if (d == null)
                return ""
            if (d.size == 0)
                return ""

            var len = d.size * 2
            val strData = ByteArray(len)
            for (i in strData.indices)
                strData[i] = '0'.toByte()
            val data = ByteArray(d.size + 1)
            data[0] = 0
            System.arraycopy(d, 0, data, 1, d.size)
            val bi = BigInteger(data)
            val src = bi.toString(16).toByteArray()
            val offset = strData.size - src.size
            len = src.size
            System.arraycopy(src, 0, strData, offset, len)
            return String(strData)
        }

        @Throws(NoSuchAlgorithmException::class)
        fun hashData(algorithm: String, b: ByteArray): ByteArray {
            val md = MessageDigest.getInstance(algorithm)
            md.update(b)
            return md.digest()
        }


        fun md5(b: ByteArray): String? {
            try {
                val a = hashData("MD5", b)
                return ByteArrayToHexString(a)
            } catch (e: NoSuchAlgorithmException) {
                return null
            }

        }

        fun sign(content: String, mackey: String): String? {
            val orgcontent = content + mackey
            return md5(orgcontent.toByteArray())
        }
    }
    //	public static void main(String[] args) {
    //		String md50="20060301|testbillmd5|123456|123456243|123|321621|http://192.168.8.243:8080/lakala2/payInform.jsp|2|9997|";
    //		String md51="20060301|testbillmd5|123456|123456243|123|321621|http://192.168.8.243:8080/lakala2/payInform.jsp|2|9997|";
    //		String str="20060301|3F010001601|lkl88uh67ij|go-29-1-aews|39900|512332|http://1234.lakala.com/order/lakala/notify.php|2|811002|";
    //		System.out.println("md50 is...."+MD5Util.md5(md50.getBytes()));
    //		System.out.println("md51 is........."+MD5Util.md5(md51.getBytes()));
    //		String md52="amount=0.01&can_pay=y&mer_id=312901&partner_bill_no=6842&partner_extendinfo=ydDC8834&partner_query_time=110819 062050&req_id=26824&sec_id=MD5&service=lakala.agency.tradePayBalance&v=1.1";
    //		String md53="amount=0.01&can_pay=y&mer_id=312901&partner_bill_no=6842&partner_extendinfo=ydDC8834&partner_query_time=110819 065341&req_id=26824&sec_id=MD5&service=lakala.agency.tradePayBalance&v=1.1123456";
    //		String md34="101720111228����vlp\\$!~$)>6/*8k^";
    //		//String ii="\";
    //		String dd="20060301|3F070001001|clxfuh4jt52uhj|20111229-45427-21536506-100730|51900|588884264| http://store.kidulty.com/cart/lakala_r|2|312895|";
    //
    //		String al="3G020000301312967120331000002TR4567JGFDW49OLNBV";
    //		System.out.println("md51 is........."+MD5Util.md5(al.getBytes()));
    //	}

}
