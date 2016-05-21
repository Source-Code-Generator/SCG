#!/usr/bin/python

import sys
import os

def classify():
    if len(sys.argv)<=1:
        print "No Path Specified Using the current Directory"
        fileList=os.listdir(os.getcwd())
        path=os.getcwd()
    else:
        fileList=os.listdir(sys.argv[1])
        path=sys.argv[1]

    fileName=os.path.basename(__file__)
    if fileName in fileList:
        fileList.remove(fileName)

    fileDict=dict()
    for i in fileList:
        val=i.split('.')
        if(fileDict.has_key(val[1])==True):
            fileDict[val[1]]=fileDict[val[1]]+[i]
            os.rename(path+'/'+i,path+'/'+val[1]+'/'+i)
        else:
            fileDict[val[1]]=[i]
            os.mkdir(path+'/'+val[1])
            os.rename(path+'/'+i,path+'/'+val[1]+'/'+i)

    print fileDict

if __name__=='__main__':
    classify()
