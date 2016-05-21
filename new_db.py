import re
import time
import datetime
import MySQLdb

def main():
    opfile=open('xev_output')
    buttonpress=re.compile('ButtonPress.*')
    buttonRelease=re.compile('ButtonRelease.*')
    noButtonPress=re.compile('(?!.*ButtonPress).*event.*')
    timestack=list()
    timeflag=0
    doubleflag=0
    windowstack=list()
    lineStack=list()
    for line in opfile:
        if(timeflag==1):
            vals=line.split()
            time=vals[5]
            timestack.append(int(time.rstrip(',')))
            timeflag=0

        if(timeflag==2):
            lineStack.append(line)
            vals=line.split()
            time=vals[5]
            timestack.append(int(time.rstrip(',')))
            doubleflag=doubleflag+1
            if(doubleflag==2):
                time1=timestack.pop()
                time2=timestack.pop()
                time3=timestack.pop()
                time4=timestack.pop()
                if(time4-time2<500):
                    temp=vals[7].split(',')
                    print temp
                    print temp[0].split('(')[1],temp[1].strip(')')
                    enterData('DoubleClick',windowstack.pop().strip(','),temp[0].split('(')[1],temp[1].strip(')'))
                else:
                    window=windowstack.pop()
                    temp=vals[7].split(',')
                    enterData('SingleClick',window.strip(','),temp[0].split('(')[1],temp[1].strip(')'))
                    enterData('singleClick',window.strip(','),temp[0].split('(')[1],temp[1].strip(')'))
                print time1,time2,time3,time4
                doubleflag=0
            timeflag=0

        val1=buttonpress.match(line)
        if val1!=None:
            timeflag=1

        val2=buttonRelease.match(line)
        if val2!=None:
            window=line.split()[7]
            windowstack.append(window)
            timeflag=2

        val3=noButtonPress.match(line)
        if(val3!=None and len(timestack)==2):
            temp=lineStack.pop().split()[7].split(',')
            timestack.pop()
            timestack.pop()
            enterData('SingleClick',windowstack.pop().strip(','),temp[0].split('(')[1],temp[1].strip(')'))
            doubleflag=0



def enterData(eventtype,window,mousex=None,mousey=None):
    ts=time.time()
    st = datetime.datetime.fromtimestamp(ts).strftime('%Y-%m-%d %H:%M:%S')
    db=MySQLdb.connect(host='localhost',
    user='root',
    passwd='InspiroN',
    db='scg'
    )
    cur=db.cursor()
    print st
    cur.execute('insert into events(eventtype,sysinfo_id,timestamp,mousex,mousey,window_id) values(%s,1,%s,%s,%s,%s)',(eventtype,st,mousex,mousey,window))
    db.commit()

if __name__=='__main__':
    main()
