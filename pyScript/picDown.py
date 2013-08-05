import urllib.request
import time
base_url = 'http://tos.hehagame.com/tj/images/60px/60px-'
'''
image type http://tos.hehagame.com/tj/images/60px/60px-402i.png
'''
pic_number='001.png'
path_name = './'
  
for r in range(1, 402):
   file_name = pic_number[:-7] + '{:03}'.format(int(pic_number[-7:-4]) - 1 + r)+"i" + pic_number[-4:]
   print(base_url + file_name)
   try:
      urllib.request.urlretrieve(base_url + file_name, path_name + file_name)
      time.sleep(1)
   except:
      print("error download:"+base_url + file_name)
