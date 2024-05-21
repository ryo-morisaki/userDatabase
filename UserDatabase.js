import React, {useState, useEffect} from "react";
import './UserDatabase.css';    

function UserDatabase() {
    const [data, setData] = useState();

    useEffect(() => {
        console.log("call useEffect Start");
        fetch('http://localhost:8080/UserDatabase').then(response => {
            response.json().then(value => {
                console.log(value);
                setData(value);
            })
        })
        .catch(error => {
            console.log(error);
            setData([]);
        });

        console.log("call useEffect END");
        return () => {};
    },[]);

    const deleteUserData = (formData) => {
        fetch('http:/localhost:8080/Userdatabase/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (response.ok) {
                return fetchUserDatabaseData();
            }else{
                console.error('Failed to delete UserData');
            }
        })
        .catch(error => {
            console.error('error deliting UserData', error);
        });
    }

    const fetchUserDatabaseData = () => {
        fetch('http://localhost:8080/UserDatabase')
        .then(response => response.json())
        .then(data => {
            setData(data);
        })
        .catch(error => {
            console.error('error fetching UserData:', error);
            setData([]);
        });
      }

    const UserData = data && data.map((item, index) => {
        return (<tr key={index}>
            <td>{item.user_id}</td>
            <td>{item.name}</td>
            <td>{item.address}</td>
            <td>{item.password}</td>
        </tr>);
    })

// 在庫情報を追加する関数
const addUserData = (formData) => {
    fetch('http://localhost:8080/UserDatabase/add', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(formData)
    })
    .then(response => {
      if (response.ok) {
        // 在庫情報が正常に追加された場合、フルーツデータを再取得して更新する
        return fetchUserDatabaseData();
      } else {
        // エラーメッセージを表示するなどの処理を行う
        console.error('Failed to add apartment');
      }
    })
    .catch(error => {
      console.error('Error adding apartment:', error);
    });

    const handleSubmit = (event) => {
        event.preventDefault();
        const formData = new FormData(event.target);
        const newUserData = {
          name: formData.get('name'),
          address: parseInt(formData.get('address')),
          salesrep: parseInt(formData.get('salesrep')),
          floorarea: parseInt(formData.get('floorarea')),
          registerdate: parseInt(formData.get('registerdate'))
    };
    addUserData(newUserData);
    deleteUserData(newUserData);
  }
  }



    return (
        <div>
            <h3>ユーザー情報</h3>
            <table border="1">
              <thead>
                 <tr>
                     <th>ユーザーID</th>
                     <th>ユーザー名</th>
                     <th>ユーザーアドレス</th>
                     <th>パスワード</th>
                 </tr>
              </thead>
              <tbody>
                {UserData}
              </tbody>
            </table>
            <th></th>
            <th></th>
            <button type="submit">追加</button>
        </div>
    );
}

export default UserDatabase;