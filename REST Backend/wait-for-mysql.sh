#!/bin/sh
# Note that this is a Unix script! If things are mysteriously failing, check that you are using Unix newlines (LF) and not Windows (CR LF).

# 'mysql' is the name we will assign to the SQL container. TODO: make something more general
while [ ! $(mysql -uroot -proot -hmysql -e"SELECT * FROM information_schema.tables WHERE table_schema = 'medical-platform' AND table_name = 'patient' LIMIT 1" medical-platform) ]
# FIXME: if `patient` table exists but is empty, script will wait forever
do
    echo "Tables not ready yet, waiting..."
    sleep 5
done

echo "Tables are ready!"
