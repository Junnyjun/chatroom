import { useState, useEffect } from 'react';

function RoomList() {
    const [rooms, setRooms] = useState([]);

    useEffect(() => {
        fetch('/api/rooms')
            .then(response => response.json())
            .then(data => setRooms(data.content));
    }, []);

    return (
        <ul>
            {rooms.map(room => (
                <li key={room.id}>
                    <h2>{room.name}</h2>
                    <p>{room.description}</p>
                </li>
            ))}
        </ul>
    );
}

export default RoomList;
