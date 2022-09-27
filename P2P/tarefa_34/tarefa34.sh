rm -rf /tmp/myhost
rm -rf /tmp/usuario1
rm -rf /tmp/usuario2


# HASHs #############
pioneiroPub=0D340D7547805C73CD6B96BA4F41814428D0EB5A5D79538C76173E87C867C5DE
pioneiroPvt=1C9B2774C6ABA269E49D3C6834FBD7A00D5659DE066CAE6CAE2E5C49BAA58F7F0D340D7547805C73CD6B96BA4F41814428D0EB5A5D79538C76173E87C867C5DE

newbiePub=ACC3D0E40B6E0480C7733F92160FA94F449EC7134DEF0B47F0C99FDB118EEE34
newbiePvt=A31EEB8A2F7FCD6C9AF5A0A6BE48FBAC69DC7545783655D1F8BC0E49D966937AACC3D0E40B6E0480C7733F92160FA94F449EC7134DEF0B47F0C99FDB118EEE34

trollPub=15E557C2CA892427F08D997944C63385E3758F12543294C95922A02D10473501
trollPvt=71B1C4ED9E657DD48AEBCEEE1889833B05D636B8CEDD7C8981DBDD1B7790D12415E557C2CA892427F08D997944C63385E3758F12543294C95922A02D10473501
#####################


echo "Simulação dia 1"

hostPioneiro=8081
hostNewbie=8082
hostTroll=8083

gnome-terminal -- freechains-host --port=$hostPioneiro start /tmp/myhost
gnome-terminal -- freechains-host --port=$hostNewbie start /tmp/usuario1
gnome-terminal -- freechains-host --port=$hostTroll start /tmp/usuario2

sleep 1s

echo "Pioneiro entrando no fórum"
freechains --host=localhost:$hostPioneiro chains join '#forum' $pioneiroPub

echo "Newbie entrando no fórum"
freechains --host=localhost:$hostNewbie chains join '#forum' $pioneiroPub

echo "Troll entrando no fórum"
freechains --host=localhost:$hostTroll chains join '#forum' $pioneiroPub

syncHost () {
    echo "Sincronizando pioneiro com o newbie"
    freechains --host=localhost:$hostPioneiro peer localhost:$hostNewbie recv '#forum'
    echo "Sincronizando pioneiro com o troll"
    freechains --host=localhost:$hostPioneiro peer localhost:$hostTroll recv '#forum'

    sleep 1s

    echo "Sincronizando newbie com o pioneiro"
    freechains --host=localhost:$hostNewbie peer localhost:$hostPioneiro recv '#forum'
    echo "Sincronizando newbie com o troll"
    freechains --host=localhost:$hostNewbie peer localhost:$hostTroll recv '#forum'

    sleep 1s

    echo "Sincronizando troll com o pioneiro"
    freechains --host=localhost:$hostTroll peer localhost:$hostPioneiro recv '#forum'
    echo "Sincronizando troll com o newbie"
    freechains --host=localhost:$hostTroll peer localhost:$hostNewbie recv '#forum'
}

getReps () {
    echo "Reputacao pioneiro"
    echo `freechains --host=localhost:$hostPioneiro chain '#forum' reps $pioneiroPub`

    echo "Reputacao newbie"
    echo `freechains --host=localhost:$hostNewbie chain '#forum' reps $newbiePub`

    echo "Reputacao troll"
    echo `freechains --host=localhost:$hostTroll chain '#forum' reps $trollPub`
}

for i in {1..91..15}
do
    echo $'\n'
    echo "\n Simulação dia $i"
    currentDate=`date +%s`
    declare -i seconds=60
    declare -i minutes=60
    declare -i hours=24
    nextTimestamp=$(($currentDate + $seconds*$minutes*$hours*$i))

    echo "Setando timestamp do pioneiro para daqui a $i dias"
    freechains-host --port=$hostPioneiro now $nextTimestamp

    echo "Setando timestamp do newbie para daqui a $i dias"
    freechains-host --port=$hostNewbie now $nextTimestamp

    echo "Setando timestamp do troll para daqui a $i dias"
    freechains-host --port=$hostTroll now $nextTimestamp

    echo "Post do dia $i do pioneiro"
    postPioneiro=`freechains --host=localhost:$hostPioneiro chain '#forum' post inline "Mensagem do pioneiro dia $i" --sign=$pioneiroPvt`

    echo "Post do dia $i do newbie"
    postNewbie=`freechains --host=localhost:$hostNewbie chain '#forum' post inline "Mensagem do newbie dia $i" --sign=$newbiePvt`

    echo "Post do dia $i do troll"
    postTroll=`freechains --host=localhost:$hostTroll chain '#forum' post inline "Mensagem do troll dia $i" --sign=$trollPvt`

    syncHost

    if [ $(($i % 2)) = 0 ]; then
        echo "Curtindo o post do pioneiro"
        freechains chain '#forum' --port=$hostNewbie like $postPioneiro --sign=$newbiePvt
    else
        echo "Curtindo o post do newbie"
        freechains chain '#forum' --port=$hostPioneiro like $postNewbie --sign=$pioneiroPvt
    fi

    if [ $i -lt 31 ]; then
        echo "Curtindo o post do troll"
        freechains chain '#forum' --port=$hostPioneiro like $postTroll --sign=$pioneiroPvt
    else
        echo "Dando dislike no post do troll"
        freechains chain '#forum' --port=$hostPioneiro dislike $postTroll --sign=$pioneiroPvt
    fi

    getReps
    sleep 2s
done

